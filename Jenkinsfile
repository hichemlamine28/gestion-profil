// donner le choix à l'utilisateur, qualif/prod/dev/uat/preprod
def projet_settings = null
def MCS_CONTAINER_IMAGE_FULLNAME = null
def env='Qualif'


    properties(
        [
            buildDiscarder(
                    logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '5',numToKeepStr: '5')
            ),
            disableConcurrentBuilds(),
            disableResume(),
            pipelineTriggers([pollSCM('* * * * *')])
        ]
    )

node('master'){

    stage('set environment'){              
               
    timeout(time: 30, unit: 'SECONDS') {   
    try {          
    def userInput = input(id: 'choix', message: 'Choisir un Environnement ', ok: 'Valider', parameters: [
    [$class: 'ChoiceParameterDefinition', name:'input', description: 'Environnement ?', choices: '\nDev\nQualif\nPreprod\nProd'],
    
    ])
    env = userInput  
    
          
    }catch (err) {
                    def user = err.getCauses()[0].getUser()
                    if ('SYSTEM' == user.toString()) { // SYSTEM means timeout
                        env= 'Qualif'     // Set default Environment to 'Qualif'
                    } else {
                        didInput = false
                        echo "Aborted by: [${user}]"
                    }
                }            
                

                
                } 
  
        echo "Environnement : ${env}"          // cette variable permet de choisir sur quel serveur on va deployer 


        USERS_TO_NOTIFY = 'hichem.elamine@breakpoint-technology.fr'
             
       
       //variable pour server         qualif -------prod									necessaire ici pour eviter BRANCH/TAG  sur SVN 
       //varibale pour idprojet       linkinnov-env-test --------- linkinnov-221611			non necessaire ici
       //variable pour cluster        linkinnov-qualif-app--------linkinnov-prod-app 		non necessaire ici
       //variable pour jsonfile       .............98c45.json -------         ....573.json	non necessaire ici
              
       

       if(env=="Qualif"){
       server='qualif'
       } 
       else if(env=='Prod'){
       server='prod'       
       } 
       else{
       server='qualif'      
       }  

       
   echo "Environnement : ${env}" 
   echo "server    =   ${server}"  
   
   }
   
   }


// Lancement du pipeline sur un esclave 

node('slave') {
    projet_settings = getConfigObject()
    def google_credential = getGoogleCredentialsObject(projet_settings)
    def pom = null

    try {

        stage('Checkout') {
            checkout scm

            if(!fileExists("./configs")) {
                checkoutConfigsKubernetes()
                /* and checkout other configs */
            } else {
                if(!fileExists("./configs/k8s/")) {
                    checkoutConfigsKubernetes()
                }
            }
        }

        dir(projet_settings.application.name) {
            // lecture du fichier pom.xml
            pom = readMavenPom file: 'pom.xml'

        withEnv(getEnvArray(projet_settings, google_credential, pom)) {
            // nous essayons d'utiliser l'image pour builder, sinon nous la crÃ©ons.
            try {
                buildImage()
                docker.image(projet_settings.docker.image.build_image.full).inside() {
                    sh '''
                    ls -al
                    '''
                }
            }
            catch(errors) {
                println "Probleme dans la creation de l'image"
                throw errors
            }

            //DÃ©marrage du container dans lequel on build l'application
            // docker.image(projet_settings.docker.image.build_image.full).inside('''
            //                                         --net=host \
            //                                         -u root:root \
            //                                         -v /root/.m2:/root/.m2 \
            //                                         -v /root/.sonar:/root/.sonar
            //                                     ''') 

            // Lancement dans un container Docker utilisant l'utilisateur Jenkins
            // Pas besoin d'Ãªtre root.
            docker.image(projet_settings.docker.image.build_image.full).inside('''
                --net=host \
                -v /etc:/etc 
                -v /var/lib/jenkins:/var/lib/jenkins 
                -v /home/jenkins:/home/jenkins 
                -v /var/run/docker.sock:/var/run/docker.sock 
                --group-add 999
            ''') {

                    withEnv(['MAVEN_PARAMS=' + projet_settings.jenkins.maven.build.params]) {
                        withCredentials(
                            [
                                file(credentialsId: projet_settings.jenkins.maven.settings.credentialId, variable: 'MAVEN_SETTINGS')
                            ]
                        ) {
                            if (!MAVEN_PARAMS.contains("-DskipTests")) {
                                /**
                                * DEBUT DE LA SECTION CRITIQUE DE BUILD
                                */
                                lock("LINKINNOV_DOCKER_TEST_ENV") {
                                    startTestEnv()
                                    build()
                                    getTestResult()
                                    stopTestEnv()
                                }
                            } else {
                                build()
                            }
                        }
                    }

                    // Si le Jenkins Client possÃ¨de un SONAR ?
                    if (projet_settings.jenkins.sonar.active == true) {
                            stage('SonarQube analysis') {
                                withSonarQubeEnv('SONARQUBE_ARKEUP') {
                                    // requires SonarQube Scanner for Maven 3.2+
                                    withEnv(['MAVEN_PARAMS=' + projet_settings.jenkins.maven.sonar.params]) {
                                        withCredentials(
                                            [
                                                file(credentialsId: projet_settings.jenkins.maven.settings.credentialId, variable: 'MAVEN_SETTINGS')
                                            ]
                                        ) {
                                            sh """
                                            mvn ${MAVEN_PARAMS}
                                            """
                                        }
                                    }
                                }
                            }

                    }

                    /**
                     * save artifacts
                     */
                    archiveArtifacts 'target/*.jar'

                    /**
                     * send artifacts to nexus
                     */
                    publish()


                  
            }


                // nous essayons d'utiliser l'image pour packager, sinon nous la crÃ©ons.
                try {
                    docker.image(projet_settings.docker.image.packager_image.full).inside() {
                        sh '''
                        find .
                        '''
                    }
                }
                catch(all) {
                    sh '''
                    pwd
                    find .
                    [ -d build ] || mkdir build
                    find .
                    '''
                    checkout(
                        [
                            $class: 'SubversionSCM', 
                            additionalCredentials: [], 
                            excludedCommitMessages: '', 
                            excludedRegions: '', 
                            excludedRevprop: '', 
                            excludedUsers: '', 
                            filterChangelog: false, 
                            ignoreDirPropChanges: false, 
                            includedRegions: '', 
                            locations: [
                                [
                                    cancelProcessOnExternalsFail: true, 
                                    credentialsId: projet_settings.jenkins.scm.credentialsId, 
                                    depthOption: 'infinity', 
                                    ignoreExternalsOption: true, 
                                    local: 'build', 
                                    remote: projet_settings.docker.image.packager_image.scm
                                ]
                            ], 
                            quietOperation: true, 
                            workspaceUpdater: [$class: 'UpdateUpdater']
                        ]
                    )
                    dir("build") {
                        sh '''
                        pwd
                        find .
                        '''
                        def build_image = null
                        build_image = docker.build(projet_settings.docker.image.packager_image.full, ' -f Dockerfile . ') 
                    }
                }

                /**
                 * CREER UNE IMAGE DOCKER POUR L'APPLICATION
                 * A faire par : Developpeur / Developpeur Operationnel
                 */

                stage('Dockerize application') {
                    //def pom = readMavenPom file: 'pom.xml'

                    MCS_CONTAINER_IMAGE_FULLNAME = getImageFullName(projet_settings, google_credential,pom)

                    echo MCS_CONTAINER_IMAGE_FULLNAME

                    SHA256SUM = sh(returnStdout: true, script: "sha256sum  ./target/" + pom.getArtifactId() + "-" + pom.getVersion() + ".jar")
                    echo SHA256SUM

                    BUILD_CMD = "build -t $MCS_CONTAINER_IMAGE_FULLNAME -f ${projet_settings.application.image.file_path} ." +
                            " --build-arg APP_MCS_NAME=" + pom.getArtifactId() +
                            " --build-arg APP_GROUP_ID=" + pom.getGroupId() +
                            " --build-arg APP_MCS_SOURCE=target " +

                            " --build-arg APP_GLOBAL_PROPERTIES_SRC=external_properties/dev " +
                            " --build-arg APP_GLOBAL_PROPERTIES_NAME=application.properties "

                    // on remettra en place quand on sera capable de mettre la clÃ© le fichier properties
                    /**
                        * " --build-arg CERTIFICATE_STOREPASS='"+SHA256SUM +"'"+
                        * " --build-arg CERTIFICATE_KEYPASS='"+SHA256SUM+ "'"
                        */
                    withEnv(['BUILD_CMD=' + BUILD_CMD]) {
                        sh '''
                        docker ${BUILD_CMD}
                        '''
                    }
                }

                /**
                 * ENVOYER UNE IMAGE DOCKER DANS UN CONTAINER REGISTRY
                 * A faire par : Developpeur OpÃ©rationnel
                 */

                docker.image(projet_settings.docker.image.push_image.full).inside('''
                    -v /etc:/etc 
                    -v /var/lib/jenkins:/var/lib/jenkins 
                    -v /home/jenkins:/home/jenkins 
                    -v /var/run/docker.sock:/var/run/docker.sock 
                    --group-add 999
                ''') {
                    withCredentials([file(credentialsId: projet_settings.jenkins.gcp.credentialsId, variable: 'GOOGLE_CLOUD_CREDENTIAL')]) {
                        stage('Push Application to Google Container Registry') {
                        

                            sh '''
                            gcloud config set project ${GOOGLE_CLOUD_PROJECT_ID}
                            gcloud -q auth activate-service-account --key-file $GOOGLE_CLOUD_CREDENTIAL
                            gcloud auth configure-docker
                            export GOOGLE_APPLICATION_CREDENTIALS=${GOOGLE_CLOUD_CREDENTIAL}
                            gcloud config list
                            docker push ${APPLICATION_IMAGE_FULL}
                            docker tag ${APPLICATION_IMAGE_FULL} ${APPLICATION_IMAGE_FULL}.b${BUILD_NUMBER}
                            docker push ${APPLICATION_IMAGE_FULL}.b${BUILD_NUMBER}
                            
                            docker rmi ${APPLICATION_IMAGE_FULL}
                            docker rmi ${APPLICATION_IMAGE_FULL}.b${BUILD_NUMBER}
                            
                            docker system prune --volumes -f
                            '''
                        }
                    }
                }

        } /* end withEnv() */
        } /* end dir() */


        /* run Image on Kubernetes */
        operateToKubernetes(PROCEDURE, projet_settings, google_credential, pom)

    } catch (e) {
        throw e
    } finally {
        // Etape de suppression du workspace
        stage('Clean Workspace') {
            cleanWs()
        }
    }
}


def publish() {
    stage("publish to nexus") {
        withEnv(['MAVEN_PARAMS=' + projet_settings.jenkins.maven.publish.params]) {
            withCredentials(
                [
                    file(credentialsId: projet_settings.jenkins.maven.settings.credentialId, variable: 'MAVEN_SETTINGS')
                ]
            ) {
                sh """
                mvn ${MAVEN_PARAMS}
                """
            }
        }
    }
}





def operateToKubernetes(PROCEDURE, projet_settings, google_credential, pom) {
        withEnv(getEnvArray(projet_settings, google_credential, pom)) {
            /**
             * OPERER L'APPLICATION DANS KUBERNETES
             * Selon la documentation : https://cloud.google.com/solutions/continuous-delivery-jenkins-kubernetes-engine
             * Selon l'exemple : https://github.com/GoogleCloudPlatform/continuous-deployment-on-kubernetes
             * A faire par : Developpeur OpÃ©rationnel
             */




                docker.image(projet_settings.docker.image.kubernetes.full).inside('''
                    -v /etc:/etc 
                    -v /var/lib/jenkins:/var/lib/jenkins 
                    -v /home/jenkins:/home/jenkins 
                    -v /var/run/docker.sock:/var/run/docker.sock 
                    --group-add 999
                ''') {
                    withCredentials([file(credentialsId: projet_settings.jenkins.gcp.credentialsId, variable: 'GOOGLE_CLOUD_CREDENTIAL')]) {

                            // echo 'show k8s config'
                            // for filename in $(find configs/k8s/${ENV_NAME}/ -type f); do cat configs/k8s/${ENV_NAME}/${filename}; done

                        stage('Operate Application in Kubernetes') {
                            sh '''
                            find .
                            gcloud auth activate-service-account --key-file ${GOOGLE_CLOUD_CREDENTIAL} --project ${GOOGLE_CLOUD_PROJECT_ID}
                            gcloud config set compute/region europe-west1
                            gcloud config set compute/zone europe-west1-b
                            EIP_ADDRESS_1=$(gcloud compute addresses list --filter="name=${EIP_ADDRESS_NAME_1}" --format="value(address)")
                            echo 'change image name in k8s config !'
                            sed -i.bak "s#GOOGLE_CLOUD_CONTAINER_REGISTRY_IMAGE_NAME#${APPLICATION_IMAGE_FULL}#" configs/k8s/${ENV_NAME}/*.yaml
                            echo 'change application name in k8s config !'
                            sed -i.bak "s#{{LINKINNOV_APP}}#linkinnov#" configs/k8s/${ENV_NAME}/*.yaml
                            sed -i.bak "s#{{LINKINNOV_STAGE}}#${ENV_NAME}#" configs/k8s/${ENV_NAME}/*.yaml
                            sed -i.bak "s#{{LINKINNOV_NAMESPACE}}#${ENV_NAME}#" configs/k8s/${ENV_NAME}/*.yaml
                            sed -i.bak "s#{{EIP_ADDRESS_1}}#${EIP_ADDRESS_1}#" configs/k8s/${ENV_NAME}/*.yaml
                            gcloud container clusters get-credentials ${GOOGLE_CLOUD_KUBERNETES_NAME} --zone=${GOOGLE_CLOUD_KUBERNETES_ZONE}
                            kubectl get ns ${GOOGLE_CLOUD_KUBERNETES_NAMESPACE} || kubectl create ns ${GOOGLE_CLOUD_KUBERNETES_NAMESPACE}
                            kubectl --namespace=${GOOGLE_CLOUD_KUBERNETES_NAMESPACE} apply -f configs/k8s/${ENV_NAME}/
                            '''
                        }
                    }
                }
        }
}

def buildImage() {
    def scmVarsBuildDocker = checkout(
        [
            $class: 'SubversionSCM', 
            additionalCredentials: [], 
            excludedCommitMessages: '', 
            excludedRegions: '', 
            excludedRevprop: '', 
            excludedUsers: '', 
            filterChangelog: false, 
            ignoreDirPropChanges: false, 
            includedRegions: '', 
            locations: [
                [
                    cancelProcessOnExternalsFail: true, 
                    credentialsId: projet_settings.jenkins.scm.credentialsId, 
                    depthOption: 'infinity', 
                    ignoreExternalsOption: true, 
                    local: './build/', 
                    remote: projet_settings.docker.image.build_image.scm
                ]
            ], 
            quietOperation: true, 
            workspaceUpdater: [$class: 'UpdateUpdater']
        ]
    )
    scmVarsBuildDocker.each {
        println it.key + " : " + it.value
    }
    dir("build") {
        sh '''
        ls -al
        '''
        def build_image = null
        build_image = docker.build(projet_settings.docker.image.build_image.full, ' -f Dockerfile . ') 
    }
}


def getConfigFileId() {
        return server + "-projet-settings"
}

def getConfigObject() {
    def var_fileId = getConfigFileId()
    configFileProvider(
        [
            configFile(fileId: var_fileId, variable: 'PROJET_SETTINGS')
        ]
    ) {
        echo "projet_settings : $PROJET_SETTINGS"
        projet_settings = readJSON file: "$PROJET_SETTINGS"
        return projet_settings
    } /* end configFileProvider */
}

def checkoutConfigsKubernetes() {
        checkout(
            [
                $class: 'SubversionSCM', 
                additionalCredentials: [], 
                excludedCommitMessages: '', 
                excludedRegions: '', 
                excludedRevprop: '', 
                excludedUsers: '', 
                filterChangelog: false, 
                ignoreDirPropChanges: false, 
                includedRegions: '', 
                locations: [
                    [
                        cancelProcessOnExternalsFail: true, 
                        credentialsId: projet_settings.jenkins.scm.credentialsId, 
                        depthOption: 'infinity', 
                        ignoreExternalsOption: true, 
                        local: './configs/k8s/' + server, 
                        remote: getKubernetesConfigsScmUrl(projet_settings)
                    ]
                ], 
                quietOperation: false, 
                workspaceUpdater: [$class: 'CheckoutUpdater']
            ]
        ) /* end checkout() */
}

def build() {
    /**
     * lancement de maven avec des paramatÃ¨res
     */
    stage('Package application') {
        sh """
        mvn ${MAVEN_PARAMS}
        """
    }
}

def getTestResult() {
    stage('Test Result') {
        junit 'target/surefire-reports/**/*.xml'
    }
}

def chmodWordspace() {
    println """ !!! DEPRECATED !!! """
    docker.image("alpine").inside('''
        -u root:root
    ''') {
        sh 'chmod -R 777 .'
    }
}

def startTestEnv() {
    println "[start Test ENV] BEGIN"
    stage('Start Test Environement') {
        sh '''
        chmod u+x *.sh
        ./dc-restart-env.sh 3
        echo "sleeping for 20s to let docker images start up"
        sleep 20
        docker ps -a
        '''
    }
    println "[start Test ENV] END"
}

def stopTestEnv() {
    println "[stop Test ENV] BEGIN"
    stage('Stop Test Environement') {
        sh '''
        ./dc-stop-env.sh 3
        echo "sleeping for 20s to let docker images shutdown"
        sleep 20
        '''
    }
    println "[stop Test ENV] END"
}

def getGoogleCredentialsObject(projet_settings){
    withCredentials(
        [
            file(credentialsId: projet_settings.jenkins.gcp.credentialsId, variable: 'GOOGLE_CLOUD_CREDENTIAL')
        ]
    ) {
        google_credential = readJSON file: GOOGLE_CLOUD_CREDENTIAL
        return google_credential
    }
}

def getEnvArray(projet_settings, google_credential, pom){
    return [
        'ENV_NAME=' + server,
        'GOOGLE_CLOUD_CREDENTIAL_ID=' + projet_settings.gcp.credentialsId,
        'GOOGLE_CLOUD_PROJECT_ID=' + google_credential.project_id,
        'GOOGLE_CLOUD_AUTH_KIND=serviceaccount',
        'GOOGLE_CLOUD_COMPUTE_SERVICE_ACCOUNT=' + projet_settings.gcp.compute_service_account,
        'GOOGLE_CLOUD_CONTAINER_REGISTRY_LOCATION=' + projet_settings.gcp.container_registry.location,
        'GOOGLE_CLOUD_CONTAINER_REGISTRY_URL=' + projet_settings.gcp.container_registry.location + '/' + google_credential.project_id + '/',
        'GOOGLE_CLOUD_KUBERNETES_NAME=' + projet_settings.gcp.kubernetes.name,
        'GOOGLE_CLOUD_KUBERNETES_ZONE=' + projet_settings.gcp.kubernetes.zone,
        'GOOGLE_CLOUD_KUBERNETES_NAMESPACE=' + server,
        'DOCKER_IMAGE_NAME=' + projet_settings.docker.image.name,
        'DOCKER_IMAGE_TAG=' + projet_settings.docker.image.tag,
        'DOCKER_IMAGE_FULL=' + projet_settings.docker.image.full,
        'APPLICATION_NAME=' + pom.getName(),
        'APPLICATION_IMAGE_NAME=' + pom.getArtifactId(),
        'APPLICATION_IMAGE_TAG=' + pom.getVersion(),
        'APPLICATION_IMAGE_FULL=' + getImageFullName(projet_settings, google_credential, pom),
        'MAVEN_SETTINGS_CREDENTIALID=' + projet_settings.jenkins.maven.settings.credentialId,
        'NEXUS_URL=' + projet_settings.jenkins.nexus.url,
        'NEXUS_REPOSITORY_ID=' + projet_settings.jenkins.nexus.repository.id,
        'NEXUS_REPOSITORY_URL=' + projet_settings.jenkins.nexus.repository.url,
        'EIP_ADDRESS_NAME_1=' + projet_settings.gcp.kubernetes.services.gestion_profil_mcs_lb.eip_address_name
    ]
}

def getImageFullName(projet_settings, google_credential, pom){
        return projet_settings.gcp.container_registry.location + '/' + google_credential.project_id + '/' + pom.getArtifactId() + ':' + pom.getVersion() + '.' + server + ".b" + ${BUILD_NUMBER}
    }


def getApplicationName(projet_settings) {
    return projet_settings.application.name
}

def getConfigsKubernetesRepo(projet_settings) {
    return projet_settings.configs.kubernetes.repository
}

def getKubernetesConfigsScmUrl(projet_settings) {
    return getConfigsKubernetesRepo(projet_settings) + "/" + server + "/" + getApplicationName(projet_settings)
}
