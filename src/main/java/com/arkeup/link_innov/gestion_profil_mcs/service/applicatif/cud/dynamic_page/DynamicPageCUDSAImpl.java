package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.dynamic_page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.dynamic_page.DynamicPageMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.dynamic_page.DynamicPageDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.dynamic_page.TagGeneratorDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.dynamic_page.DynamicPageCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.dynamic_page.DynamicPageRSM;

import java.util.Arrays;
import java.util.List;

/**
 * @author Stéphan R.
 */
@Service
public class DynamicPageCUDSAImpl implements DynamicPageCUDSA {

    @Autowired
    private DynamicPageCUDSM dynamicPageCUDSM;

    @Autowired
    private DynamicPageRSM dynamicPageRSM;

    @Autowired
    private DynamicPageMapper dynamicPageMapper;

    /* (non-Javadoc)
     * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.dynamic_page.DynamicPageCUDSA#save(com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.DynamicPageDTO)
     */
    @Override
    public DynamicPageDTO save(DynamicPageDTO dynamicPageDTO) {
        dynamicPageDTO.setTag(new TagGeneratorDTO().toString());

        return this.dynamicPageMapper.toDTO(this.dynamicPageCUDSM.save(this.dynamicPageMapper.toDO(dynamicPageDTO)));
    }

    /* (non-Javadoc)
     * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.dynamic_page.DynamicPageCUDSA#update(com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.dynamic_page.DynamicPageDTO)
     */
    @Override
    public DynamicPageDTO update(DynamicPageDTO dynamicPageDTO) {
        // Check whether or not the object exist
        if (this.dynamicPageRSM.isExist(dynamicPageDTO.getId())) {
            // Increment tag
            TagGeneratorDTO tagGeneratorDTO = new TagGeneratorDTO().buildFromString(dynamicPageDTO.getTag());
            tagGeneratorDTO.increment();

            dynamicPageDTO.setTag(tagGeneratorDTO.toString());

            return this.dynamicPageMapper.toDTO(this.dynamicPageCUDSM.save(this.dynamicPageMapper.toDO(dynamicPageDTO), true));
        }

        dynamicPageDTO.setError(true);
        dynamicPageDTO.setErrorCode(ErrorsEnum.ERR_MCS_PAGE_NOT_EXIST.getErrorCode());
        dynamicPageDTO.setMessage(ErrorsEnum.ERR_MCS_PAGE_NOT_EXIST.getErrorMessage());

        return dynamicPageDTO;
    }

    /* (non-Javadoc)
     * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.dynamic_page.DynamicPageCUDSA#deleteById(java.lang.String)
     */
    @Override
    public Boolean deleteById(String dynamicPageId) {
        return this.dynamicPageCUDSM.deleteById(dynamicPageId);
    }

    @Override
    public void initDatabase() {
        List<String> idPages = Arrays.asList("uuid-ml", "uuid-cgud", "uuid-cgv", "uuid-prad", "uuid-prud", "uuid-pc");
        String content = "<p>" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut non tristique tortor. Integer lectus metus, malesuada a posuere nec, sollicitudin sed metus." +
                "Nullam ultricies lorem non erat lobortis gravida." +
                "Ut egestas dui vitae augue sollicitudin posuere. Fusce id aliquet nisi. Nulla eget porta leo. Cras fringilla convallis turpis nec porta. Mauris suscipit" +
                " iaculis urna, eu egestas libero efficitur vel." + "Vivamus facilisis, enim ac gravida vehicula, diam nulla accumsan libero, vehicula aliquam ligula sapien " +
                "eget ipsum. Phasellus ex augue, convallis eleifend convallis eget, tincidunt in odio. Phasellus" +
                "accumsan iaculis risus, eu tempor nibh maximus sit amet. Ut eros ex, pretium sed bibendum at, malesuada eget eros. In lobortis porttitor arcu, eu " +
                "luctus urna vulputate eget. Donec turpis lectus, pharetra " +
                "vel mauris ac, pretium ultrices diam." +
                "</p>" +
                "<p>" +
                "Vestibulum et velit vel lacus venenatis efficitur efficitur at magna. Phasellus posuere purus lectus, sit amet tincidunt nulla pellentesque rhoncus. Nulla mattis tellus quis iaculis malesuada. In hac" + "habitasse platea dictumst. Curabitur lectus justo, suscipit et dictum a, sollicitudin ullamcorper elit. Suspendisse id risus eu magna bibendum luctus quis ut libero. Ut finibus semper ex, non posuere" + "eros sodales sit amet. Duis congue vehicula sapien, ut tristique justo viverra sed. Ut sodales a leo non feugiat. Maecenas mollis ante augue, eget mattis urna pulvinar ut. Nunc fringilla tristique eros" + "sed laoreet. Nam eu quam ultrices neque congue dignissim et nec diam. Praesent in sem cursus, tempus mi eget, volutpat metus." +
                "</p>" +
                "<p>" +
                "Fusce vehicula nunc at augue fringilla euismod. Aenean pretium sem sit amet risus tristique venenatis. Donec quis laoreet eros. Proin eget nisl in risus " +
                "iaculis vestibulum quis et dui. Morbi varius diam " +
                "neque, eget maximus mi laoreet a.Nunc nec volutpat quam, a tristique nisl.Proin ut consectetur ex." +
                "</p>" +
                "<p>" +
                "Pellentesque ante tellus, elementum vel iaculis ultrices, consequat id diam. Sed viverra eleifend magna, vel facilisis tortor sodales quis. Nullam cursus tellus et diam venenatis fermentum. Vestibulum" + "ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Proin vel pulvinar purus. Fusce gravida mi convallis ipsum suscipit sollicitudin. Vivamus pretium libero eu magna blandit" + "condimentum. Cras suscipit lacus ipsum, eget tincidunt eros aliquet id. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec dapibus semper odio, et feugiat est" + "interdum id." +
                "</p>" +
                "<p>" +
                "Ut non massa iaculis, convallis turpis non, faucibus risus. Cras placerat vehicula sapien, sed lobortis metus congue quis. Ut eget velit id lacus semper euismod ac vitae tortor. Sed volutpat, purus a" + "finibus blandit, nunc turpis volutpat massa, quis tempus erat quam in lacus. Nunc malesuada mi eget libero viverra, at faucibus urna congue. Donec imperdiet ligula non fringilla maximus. Nullam non" + "sagittis nibh. Morbi accumsan libero congue neque fringilla rutrum. Morbi nec interdum augue. Cras imperdiet ultricies dignissim." +
                "</p>";

        StringBuilder sb = new StringBuilder();
        sb.append("<style>\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 100;\n" +
                "      src: local('Montserrat Thin'), local('Montserrat-Thin'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUQjIg1_i6t8kCHKm45_QpRxC7mw9c.woff2) format('woff2');\n" +
                "      unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "    }\n" +
                "    /* cyrillic */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 100;\n" +
                "      src: local('Montserrat Thin'), local('Montserrat-Thin'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUQjIg1_i6t8kCHKm45_QpRzS7mw9c.woff2) format('woff2');\n" +
                "      unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "    }\n" +
                "    /* vietnamese */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 100;\n" +
                "      src: local('Montserrat Thin'), local('Montserrat-Thin'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUQjIg1_i6t8kCHKm45_QpRxi7mw9c.woff2) format('woff2');\n" +
                "      unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "    }\n" +
                "    /* latin-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 100;\n" +
                "      src: local('Montserrat Thin'), local('Montserrat-Thin'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUQjIg1_i6t8kCHKm45_QpRxy7mw9c.woff2) format('woff2');\n" +
                "      unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "    }\n" +
                "    /* latin */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 100;\n" +
                "      src: local('Montserrat Thin'), local('Montserrat-Thin'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUQjIg1_i6t8kCHKm45_QpRyS7m.woff2) format('woff2');\n" +
                "      unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "    }\n" +
                "    /* cyrillic-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 200;\n" +
                "      src: local('Montserrat ExtraLight'), local('Montserrat-ExtraLight'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_aZA3gTD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "    }\n" +
                "    /* cyrillic */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 200;\n" +
                "      src: local('Montserrat ExtraLight'), local('Montserrat-ExtraLight'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_aZA3g3D_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "    }\n" +
                "    /* vietnamese */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 200;\n" +
                "      src: local('Montserrat ExtraLight'), local('Montserrat-ExtraLight'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_aZA3gbD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "    }\n" +
                "    /* latin-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 200;\n" +
                "      src: local('Montserrat ExtraLight'), local('Montserrat-ExtraLight'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_aZA3gfD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "    }\n" +
                "    /* latin */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 200;\n" +
                "      src: local('Montserrat ExtraLight'), local('Montserrat-ExtraLight'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_aZA3gnD_g.woff2) format('woff2');\n" +
                "      unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "    }\n" +
                "    /* cyrillic-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 300;\n" +
                "      src: local('Montserrat Light'), local('Montserrat-Light'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_cJD3gTD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "    }\n" +
                "    /* cyrillic */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 300;\n" +
                "      src: local('Montserrat Light'), local('Montserrat-Light'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_cJD3g3D_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "    }\n" +
                "    /* vietnamese */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 300;\n" +
                "      src: local('Montserrat Light'), local('Montserrat-Light'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_cJD3gbD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "    }\n" +
                "    /* latin-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 300;\n" +
                "      src: local('Montserrat Light'), local('Montserrat-Light'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_cJD3gfD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "    }\n" +
                "    /* latin */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 300;\n" +
                "      src: local('Montserrat Light'), local('Montserrat-Light'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_cJD3gnD_g.woff2) format('woff2');\n" +
                "      unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "    }\n" +
                "    /* cyrillic-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 400;\n" +
                "      src: local('Montserrat Regular'), local('Montserrat-Regular'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUSjIg1_i6t8kCHKm459WRhyzbi.woff2) format('woff2');\n" +
                "      unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "    }\n" +
                "    /* cyrillic */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 400;\n" +
                "      src: local('Montserrat Regular'), local('Montserrat-Regular'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUSjIg1_i6t8kCHKm459W1hyzbi.woff2) format('woff2');\n" +
                "      unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "    }\n" +
                "    /* vietnamese */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 400;\n" +
                "      src: local('Montserrat Regular'), local('Montserrat-Regular'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUSjIg1_i6t8kCHKm459WZhyzbi.woff2) format('woff2');\n" +
                "      unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "    }\n" +
                "    /* latin-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 400;\n" +
                "      src: local('Montserrat Regular'), local('Montserrat-Regular'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUSjIg1_i6t8kCHKm459Wdhyzbi.woff2) format('woff2');\n" +
                "      unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "    }\n" +
                "    /* latin */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 400;\n" +
                "      src: local('Montserrat Regular'), local('Montserrat-Regular'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUSjIg1_i6t8kCHKm459Wlhyw.woff2) format('woff2');\n" +
                "      unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "    }\n" +
                "    /* cyrillic-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 500;\n" +
                "      src: local('Montserrat Medium'), local('Montserrat-Medium'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_ZpC3gTD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "    }\n" +
                "    /* cyrillic */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 500;\n" +
                "      src: local('Montserrat Medium'), local('Montserrat-Medium'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_ZpC3g3D_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "    }\n" +
                "    /* vietnamese */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 500;\n" +
                "      src: local('Montserrat Medium'), local('Montserrat-Medium'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_ZpC3gbD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "    }\n" +
                "    /* latin-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 500;\n" +
                "      src: local('Montserrat Medium'), local('Montserrat-Medium'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_ZpC3gfD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "    }\n" +
                "    /* latin */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 500;\n" +
                "      src: local('Montserrat Medium'), local('Montserrat-Medium'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_ZpC3gnD_g.woff2) format('woff2');\n" +
                "      unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "    }\n" +
                "    /* cyrillic-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 600;\n" +
                "      src: local('Montserrat SemiBold'), local('Montserrat-SemiBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_bZF3gTD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "    }\n" +
                "    /* cyrillic */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 600;\n" +
                "      src: local('Montserrat SemiBold'), local('Montserrat-SemiBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_bZF3g3D_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "    }\n" +
                "    /* vietnamese */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 600;\n" +
                "      src: local('Montserrat SemiBold'), local('Montserrat-SemiBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_bZF3gbD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "    }\n" +
                "    /* latin-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 600;\n" +
                "      src: local('Montserrat SemiBold'), local('Montserrat-SemiBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_bZF3gfD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "    }\n" +
                "    /* latin */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 600;\n" +
                "      src: local('Montserrat SemiBold'), local('Montserrat-SemiBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_bZF3gnD_g.woff2) format('woff2');\n" +
                "      unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "    }\n" +
                "    /* cyrillic-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 700;\n" +
                "      src: local('Montserrat Bold'), local('Montserrat-Bold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_dJE3gTD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "    }\n" +
                "    /* cyrillic */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 700;\n" +
                "      src: local('Montserrat Bold'), local('Montserrat-Bold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_dJE3g3D_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "    }\n" +
                "    /* vietnamese */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 700;\n" +
                "      src: local('Montserrat Bold'), local('Montserrat-Bold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_dJE3gbD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "    }\n" +
                "    /* latin-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 700;\n" +
                "      src: local('Montserrat Bold'), local('Montserrat-Bold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_dJE3gfD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "    }\n" +
                "    /* latin */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 700;\n" +
                "      src: local('Montserrat Bold'), local('Montserrat-Bold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_dJE3gnD_g.woff2) format('woff2');\n" +
                "      unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "    }\n" +
                "    /* cyrillic-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 800;\n" +
                "      src: local('Montserrat ExtraBold'), local('Montserrat-ExtraBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_c5H3gTD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "    }\n" +
                "    /* cyrillic */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 800;\n" +
                "      src: local('Montserrat ExtraBold'), local('Montserrat-ExtraBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_c5H3g3D_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "    }\n" +
                "    /* vietnamese */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 800;\n" +
                "      src: local('Montserrat ExtraBold'), local('Montserrat-ExtraBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_c5H3gbD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "    }\n" +
                "    /* latin-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 800;\n" +
                "      src: local('Montserrat ExtraBold'), local('Montserrat-ExtraBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_c5H3gfD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "    }\n" +
                "    /* latin */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 800;\n" +
                "      src: local('Montserrat ExtraBold'), local('Montserrat-ExtraBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_c5H3gnD_g.woff2) format('woff2');\n" +
                "      unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "    }\n" +
                "    /* cyrillic-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 900;\n" +
                "      src: local('Montserrat Black'), local('Montserrat-Black'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_epG3gTD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "    }\n" +
                "    /* cyrillic */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 900;\n" +
                "      src: local('Montserrat Black'), local('Montserrat-Black'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_epG3g3D_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "    }\n" +
                "    /* vietnamese */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 900;\n" +
                "      src: local('Montserrat Black'), local('Montserrat-Black'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_epG3gbD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "    }\n" +
                "    /* latin-ext */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 900;\n" +
                "      src: local('Montserrat Black'), local('Montserrat-Black'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_epG3gfD_u50.woff2) format('woff2');\n" +
                "      unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "    }\n" +
                "    /* latin */\n" +
                "    @font-face {\n" +
                "      font-family: 'Montserrat';\n" +
                "      font-style: normal;\n" +
                "      font-weight: 900;\n" +
                "      src: local('Montserrat Black'), local('Montserrat-Black'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_epG3gnD_g.woff2) format('woff2');\n" +
                "      unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "    }\n" +
                "    </style>");
        sb.append("<style>\n" +
                "        .body-CGU{\n" +
                "            font-family: \"Montserrat\";\n" +
                "            font-size: 12px;\n" +
                "            max-width: 1280px;\n" +
                "            background: #fff;\n" +
                "            margin: auto;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU{\n" +
                "            padding: 10px;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU a{\n" +
                "            color: #006DB6;\n" +
                "        }\n" +
                "        .body-CGU h1{\n" +
                "            text-align: center;\n" +
                "            font-size: 39px;\n" +
                "            margin-bottom: 15px;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU h2{\n" +
                "            margin-top: 40px;\n" +
                "            font-size: 15px;\n" +
                "            background-color: #EDEDED;\n" +
                "            padding: 2px 5px;\n" +
                "            letter-spacing: 1px;\n" +
                "            margin-bottom: 15px;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU h3{\n" +
                "            font-size: 14px;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "        \n" +
                "        .body-CGU .main-CGU h4{\n" +
                "          font-size: 13px;\n" +
                "          margin-bottom: 10px;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU article{\n" +
                "          margin-bottom: 20px;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU p{\n" +
                "          margin-block-start: 13px;\n" +
                "          margin-block-end: 13px;\n" +
                "          margin-inline-start: 0px;\n" +
                "          margin-inline-end: 0px;\n" +
                "          padding-bottom: 0;\n" +
                "          line-height: normal;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU .fnt-14px{\n" +
                "          font-size: 14px;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU .small-caps{\n" +
                "          font-variant: small-caps;\n" +
                "        }\n" +
                "        .body-CGU .logo-lkv{\n" +
                "            width: 258px;\n" +
                "            max-width: 80%;\n" +
                "        }\n" +
                "        .body-CGU .header-CGU{\n" +
                "            display: block;\n" +
                "            padding: 120px 0;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "        \n" +
                "        .header-CGU img{\n" +
                "            display: block;\n" +
                "            margin: auto;\n" +
                "        }\n" +
                "\n" +
                "        .body-CGU .main-CGU .important-blc{\n" +
                "            font-weight: bold;\n" +
                "            font-size: 13px;\n" +
                "            padding: 10px 20px;\n" +
                "            border: 1px #f00 solid;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU ul{\n" +
                "          padding-left:40px;\n" +
                "          list-style-type: disc;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU ul li{\n" +
                "          margin-bottom: 6px;\n" +
                "          text-align: left;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU .list > li{\n" +
                "            margin-bottom: 15px;\n" +
                "        }\n" +
                "        \n" +
                "        .body-CGU .main-CGU .list-square{\n" +
                "          list-style-type: square;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU .list-disc{\n" +
                "          list-style-type: disc;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU .list-none{\n" +
                "          list-style-type: none;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU .list-dashed{\n" +
                "          list-style-type: none;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU .list-dashed > li{\n" +
                "          text-indent: -26px;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU .list-dashed >li:before{\n" +
                "          content:'-';\n" +
                "          padding-right: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .body-CGU .main-CGU .list-underlined li{\n" +
                "          text-decoration: underline;\n" +
                "        }\n" +
                "        .body-CGU .main-CGU .list h4{\n" +
                "          font-weight: normal;\n" +
                "          font-style: italic;\n" +
                "        }\n" +
                "    </style>");
        sb.append(" <div class=\"body-CGU\">\n" +
                "        <div class=\"header-CGU\">\n" +
                "            <h1>Conditions Générales d’Utilisation  </h1>\n" +
                "            <img class=\"logo-lkv\" alt=\"logo-linkinnov\" src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAA88AAAKUCAYAAAA6tQpDAAAACXBIWXMAACE4AAAhOAFFljFgAAAFIGlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS42LWMxNDUgNzkuMTYzNDk5LCAyMDE4LzA4LzEzLTE2OjQwOjIyICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RFdnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ0MgMjAxOSAoTWFjaW50b3NoKSIgeG1wOkNyZWF0ZURhdGU9IjIwMTktMDEtMjFUMTE6NDE6MjYrMDE6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDE5LTAzLTE1VDExOjQ3OjA1KzAxOjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE5LTAzLTE1VDExOjQ3OjA1KzAxOjAwIiBkYzpmb3JtYXQ9ImltYWdlL3BuZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgcGhvdG9zaG9wOklDQ1Byb2ZpbGU9InNSR0IgSUVDNjE5NjYtMi4xIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOmQyYjM1ZDg1LTRk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' />\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"main-CGU\">\n" +
                "            <h2>PREAMBULE</h2>\n" +
                "            <article>\n" +
                "                <p>La société LINKINNOV, Société par actions simplifiée, au capital social de 10.000 euros, immatriculée au Registre du Commerce et des Sociétés de Paris sous le numéro 854 060 399, dont le siège social est sis 37, rue de Lyon, 75012 – PARIS (<i>ci-après « LINKINNOV »</i> ou <i>« l’Opérateur »</i>) édite et exploite la Plateforme internet accessible au travers du domaine <a href=\"https//:www.linkinnov.com\">www.linkinnov.com et de ses sous-domaines</a> (<i>ci-après « la Plateforme »</i>). </p>\n" +
                "\n" +
                "                <p>L’Opérateur édite et exploite une plateforme en ligne mettant en relation des chercheurs universitaires (<i>ci-après les « Académiques »</i>), des industriels (<i>ci-après les « Industriels »</i>) et d’autres acteurs de l’innovation (<i>ci-après les « Autres acteurs de l’innovation »</i>).</p>\n" +
                "\n" +
                "                <p>Dans ce cadre, il est appelé que LINKINNOV intervient en tant qu’opérateur de plateforme en ligne. Son rôle est limité à l’hébergement des contenus mis en ligne par les utilisateurs sur la Plateforme et à la mise en relation de ces derniers entre eux. </p>\n" +
                "\n" +
                "                <p class=\"important-blc\">TOUTE UTILISATION EFFECTUEE A QUEL QUE TITRE QUE CE SOIT DE LA PLATEFORME IMPLIQUE OBLIGATOIREMENT L'ACCEPTATION SANS RESERVE, PAR L’UTILISATEUR, DES PRESENTES CONDITIONS GENERALES D’UTILISATION (CGU).</p>\n" +
                "            </article>\n" +
                "\n" +
                "            <h2>ARTICLE 1. DEFINITIONS </h2>\n" +
                "            <article>\n" +
                "                <p>Les termes, mentionnés ci-dessous, ont dans les présentes Conditions Générales d’Utilisation, la signification suivante :</p>\n" +
                "                <ul class=\"list\">\n" +
                "                    <li><strong>« Académique »</strong> : désigne toute personne physique accédant à la Plateforme et procédant à la création d’un Profil en vue de mettre en ligne du Contenu.</li>\n" +
                "                    <li><strong>« Annonce »</strong> : désigne toute offre publiée par un Utilisateur sur la Plateforme en vue de la réalisation d’une Prestation.</li>\n" +
                "                    <li><strong>« Annonceur »</strong> : désigne tout professionnel disposant d’un Compte et mettant en ligne des Annonces sur la Plateforme.  </li>\n" +
                "                    <li><strong> « Compte »</strong> : désigne l’interface hébergée sur la Plateforme dans laquelle est regroupée l’ensemble des données fournies par l’Utilisateur. L’accès au Compte se fait grâce aux Identifiants.</li>\n" +
                "                    <li><strong>« Conditions Générales d’Utilisation »</strong> ou <strong>« CGU »</strong> ou <strong>« Contrat »</strong> : désigne les présentes conditions contractuelles mises à disposition sur la page d’accueil de la Plateforme, afin d’encadrer l’utilisation de celle-ci par tout Utilisateur.    </li>\n" +
                "                    <li><strong>« Contenus »</strong> : désigne l’ensemble des informations, textes, logos, marques, animations, dessins et modèles, photographies, données, liens hypertextes et de façon générale tous les éléments et contenus publiés sur la Plateforme par l’Utilisateur. </li>\n" +
                "                    <li><strong>« Contrat de Prestation de Service »</strong> : désigne le contrat conclu entre deux Utilisateurs, encadrant la fourniture d’une Prestation dont les caractéristiques peuvent avoir été définies par l’intermédiaire de la Plateforme. </li>\n" +
                "                    <li><strong>« Identifiants »</strong> : désigne l’adresse email de l’Utilisateur et le mot de passe choisis par ce dernier, nécessaires pour accéder à la Plateforme.  </li>\n" +
                "                    <li><strong>« Industriels »</strong> : désigne toute personne morale professionnelle accédant à la Plateforme notamment en vue de bénéficier d’une Prestation.</li>\n" +
                "                    <li><strong>« Parties »</strong> : désigne ensemble LINKINNOV et l’Utilisateur. </li>\n" +
                "                    <li><strong>« Plateforme »</strong> : désigne la Plateforme en ligne accessible au travers du domaine www.linkinnov.com et de ses sous-domaines. La Plateforme regroupe l’ensemble des pages web, services et fonctionnalités proposés aux Utilisateurs</li>\n" +
                "                    <li><strong>« Prestation »</strong> : désigne toute prestation intellectuelle pouvant être proposée en réponse à une Annonce, via la Plateforme. </li>\n" +
                "                    <li><strong>« Services »</strong> : désigne l’ensemble des services proposés par LINKINNOV aux Utilisateurs par l’intermédiaire de la Plateforme, et notamment la mise en relation des Utilisateurs.</li>\n" +
                "                    <li><strong>« Utilisateur »</strong> : désigne toute personne qui accède et navigue sur la Plateforme, qu’il soit Académique, Industriel ou Autre acteur de l’innovation.</li>                        \n" +
                "                </ul>\n" +
                "            </article>\n" +
                "\n" +
                "\n" +
                "            <h2>ARTICLE 2. OBJET</h2>\n" +
                "            <article>\n" +
                "              <p>\n" +
                "                  Les présentes Conditions Générales d’Utilisation conclues entre LINKINNOV et l’Utilisateur ont pour objet de fixer les dispositions contractuelles relatives aux droits et obligations respectifs des Parties dans le cadre de l’utilisation de la Plateforme et de l’ensemble des Services qui y sont proposés. \n" +
                "              </p>\n" +
                "            </article>  \n" +
                "\n" +
                "            <h2>ARTICLE 3. ACCEPTATION DES CONDITIONS GENERALES D’UTILISATION</h2>\n" +
                "            <article>\n" +
                "              <p>\n" +
                "                  L’utilisation des fonctionnalités de la Plateforme et des Services implique l’acceptation des présentes CGU.\n" +
                "              </p>\n" +
                "              <p>\n" +
                "                  Ainsi, l’Utilisateur s’engage à lire attentivement les présentes Conditions Générales d’Utilisation lors de l’accès à la Plateforme et est invité à les télécharger, les imprimer et à en conserver une copie.\n" +
                "              </p>\n" +
                "              <p>\n" +
                "                  Il est précisé que les présentes CGU sont référencées en bas de chaque page de la Plateforme au moyen d’un lien hypertexte et peuvent ainsi être consultées à tout moment. \n" +
                "              </p>\n" +
                "            </article>\n" +
                "\n" +
                "            <h2>ARTICLE 4. SPECIFICATIONS TECHNIQUES  </h2>\n" +
                "            <article>\n" +
                "              <p>\n" +
                "                  En utilisant la Plateforme, l’Utilisateur reconnaît disposer des moyens et compétences nécessaires à l’utilisation des fonctionnalités proposées sur la Plateforme.  \n" +
                "              </p>\n" +
                "              <p>\n" +
                "                  Les équipements nécessaires à l’accès et à l’utilisation de la Plateforme sont à la charge de l’Utilisateur, de même que les frais de télécommunications éventuellement induits par leur utilisation. \n" +
                "              </p>\n" +
                "            </article>");

        sb.append("<h2>ARTICLE 5. ROLE DE L’OPERATEUR  </h2>\n" +
                "            <section>\n" +
                "              <ul class=\"list list-square\">\n" +
                "                \n" +
                "                <li><h3>Article 5.1. Teneur de l’intervention de LINKINNOV</h3></li>\n" +
                "                <article>\n" +
                "                  <p>\n" +
                "                      La Plateforme éditée par LINKINNOV consiste en un service d’intermédiation en ligne mettant en relation les différents Utilisateurs.  \n" +
                "                  </p>\n" +
                "                  <p>\n" +
                "                      LINKINNOV n’exerce aucun contrôle sur l’exécution des Prestations.\n" +
                "                  </p>\n" +
                "                  <p>\n" +
                "                      Le contrat de prestations de service est donc conclu exclusivement et directement entre les Utilisateurs, LINKINNOV n’intervenant ni dans la formation ni dans l’exécution de celui-ci. \n" +
                "                  </p>\n" +
                "                  <p>\n" +
                "                      Les indications sur les tarifs et les descriptions des Prestations sont établies par les Académiques, qui en sont dès lors seuls responsables. \n" +
                "                  </p>\n" +
                "                  <p>\n" +
                "                      Les présentes Conditions Générales d’Utilisation ne confèrent en aucun cas aux Utilisateurs la qualité de salarié, mandataire, agent ou représentant de LINKINNOV.\n" +
                "                  </p>\n" +
                "                </article>\n" +
                "\n" +
                "                <li><h3>Article 5.2. Obligation générale d’information précontractuelle : loyauté, clarté et transparence</h3></li>\n" +
                "                <article>\n" +
                "                  <p>\n" +
                "                      En tant qu’opérateur de plateforme en ligne, LINKINNOV agit de manière neutre, claire et transparente.   \n" +
                "                  </p>\n" +
                "                  <p>\n" +
                "                      LINKINNOV s’engage à ne pas privilégier ni influencer le classement ou le référencement d’un Utilisateur ou d’une Annonce.\n" +
                "                  </p>\n" +
                "                  <p>\n" +
                "                      Les Annonces présentées aux Utilisateurs sur la Plateforme sont référencées par catégorie puis par ordre de pertinence par rapport à la recherche de l’Utilisateur sur le moteur de recherche de la Plateforme.\n" +
                "                  </p>\n" +
                "                  <p>\n" +
                "                      Lorsque plusieurs Annonces de même catégorie sont disponibles, elles sont classées dans un ordre antéchronologique, basé sur la date d’intégration de l’Annonce sur la Plateforme.\n" +
                "                  </p>\n" +
                "                  <p>\n" +
                "                      L’Utilisateur peut sélectionner un ou plusieurs filtres pour modifier l’ordre d’apparition des Annonces.\n" +
                "                  </p>\n" +
                "                  <p>\n" +
                "                      SI le nombre de visiteur unique mensuel sur la Plateforme est supérieur à cinq millions, LINKINNOV diffusera un document d’information sur les bonnes pratiques qu’elle doit respecter en tant qu’opérateur de plateforme en ligne.\n" +
                "                  </p>\n" +
                "                </article>\n" +
                "              <ul>\n" +
                "            </section>\n" +
                "            ");

        sb.append("  <h2>ARTICLE 6. CONDITIONS D’ACCES ET D’INSCRIPTION  </h2>\n" +
                "            <section>\n" +
                "              <p>\n" +
                "                  Tout visiteur doit impérativement disposer d’un Compte pour accéder à la Plateforme et bénéficier des Services décrits à l’article 8.1 des présentes CGU.\n" +
                "              </p>\n" +
                "              <p>\n" +
                "                  Le visiteur souhaitant utiliser la Plateforme est invité à suivre les étapes décrites ci-après :\n" +
                "              </p>\n" +
                "              <ul class=\"list list-square\">\n" +
                "                <li><h3>Article 6.1. Procédure d’inscription</h3></li>\n" +
                "                <article>\n" +
                "                  <p>\n" +
                "                      Tout Utilisateur souhaitant bénéficier des Services de la Plateforme devra préalablement se créer un Compte.\n" +
                "                  </p>\n" +
                "                  <p>\n" +
                "                      L’inscription sur la Plateforme est gratuite.\n" +
                "                  </p>\n" +
                "                  <p>\n" +
                "                      Pour être inscrit en tant qu’Utilisateur, le visiteur devra suivre les étapes suivantes : \n" +
                "                  </p>\n" +
                "                  <ul class=\"list-disc\">\n" +
                "                      <li>Le visiteur se rend sur la page d’inscription ;</li>\n" +
                "                      <li>Le visiteur choisit de s’inscrire en tant qu’Académique, Industriel ou Autre acteur de l’innovation ;</li>\n" +
                "                      <li>Le visiteur remplit le formulaire d’inscription ;</li>\n" +
                "                      <li>Le visiteur reçoit un. mail d’activation du Compte ;</li>\n" +
                "                      <li>Le visiteur est redirigé sur une page pour la configuration du mot de passe ;</li>\n" +
                "                      <li>Le Compte est validé et l’Utilisateur est connecté.</li>\n" +
                "                      \n" +
                "                  </ul>\n" +
                "                  <p>\n" +
                "                      Tout Utilisateur garantit au moment de la validation de son inscription que les données qu’il communique sont exactes, sincères et conformes à la réalité. En cas de modification de sa situation, l’Utilisateur devra immédiatement mettre à jour les informations communiquées à LINKINNOV.\n" +
                "                  </p>\n" +
                "                </article>\n" +
                "\n" +
                "                <li><h3>Article 6.2. Gestion des Identifiants</h3></li>\n" +
                "                <article>\n" +
                "                  <ul class=\"list list-none list-underlined\">\n" +
                "                    <li><h4>Article 6.2.1. Compte </h4></li>  \n" +
                "                    <article>\n" +
                "                      <p>\n" +
                "                          L’Utilisateur s’engage à fournir à LINKINNOV des données exactes, loyales et à jour, qui ne portent pas atteinte, à quelque titre que ce soit, aux droits des tiers et à communiquer à LINKINNOV toute mise à jour nécessaire des données communiquées lors de son inscription. \n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          L’Utilisateur est entièrement responsable de l’exactitude et de la mise à jour des données communiquées dans le cadre de l’ouverture et de la gestion de son Compte.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          L’Utilisateur s’engage à ne créer qu’un seul et unique Compte sur la Plateforme. LINKINNOV décline toute responsabilité quant aux conséquences dommageables que pourrait avoir l’utilisation de Comptes multiples pour un seul Utilisateur. \n" +
                "                      </p>\n" +
                "                    </article>\n" +
                "\n" +
                "                    <li><h4>Article 6.2.2. Identifiants </h4></li>\n" +
                "                    <article>\n" +
                "                      <p>\n" +
                "                          L’adresse e-mail et le mot de passe constituent les Identifiants de l’Utilisateur. \n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          L’Utilisateur sera seul responsable de l'utilisation de ses Identifiants ou des actions faites par l'intermédiaire de son Compte.\n" +
                "                          Dans le cas où un Utilisateur divulguerait ou utiliserait ses Identifiants de façon contraire à leur destination, LINKINNOV pourra alors supprimer le Compte sans préavis ni indemnité. \n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          En aucun cas, LINKINNOV ne saurait être tenu responsable en cas d’usurpation de l’identité d’un Utilisateur. Tout accès et action effectués à partir du Compte d’un Utilisateur seront présumés être effectués par cet Utilisateur, dans la mesure où LINKINNOV n’a pas pour obligation et ne dispose pas des moyens techniques lui permettant de s’assurer de l’identité des personnes ayant accès à la Plateforme à partir d’un Compte.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          Toute perte, détournement, ou utilisation non autorisée des Identifiants d’un Utilisateur et leurs conséquences relèvent de la seule responsabilité de l’Utilisateur, ce dernier étant tenu d’en avertir LINKINNOV, sans délai.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          En cas de perte de son mot de passe, l’Utilisateur pourra cliquer sur le lien « Mot de passe oublié » sur la page de la Plateforme lui permettant d’accéder à son Compte. \n" +
                "                      </p>\n" +
                "                    </article>\n" +
                "                  </ul>\n" +
                "                </article>\n" +
                "\n" +
                "                <li><h3>Article 6.3. Désinscription de l’Utilisateur</h3></li>\n" +
                "                <article>\n" +
                "                  <p>\n" +
                "                      L’Utilisateur peut à tout moment supprimer son Compte à partir dans les paramètres du compte. \n" +
                "                  </p>\n" +
                "                  <p>\n" +
                "                      LINKINNOV procèdera dans les meilleurs délais à une désactivation du Compte et adressera à l’Utilisateur un courriel lui demandant s’il souhaite procéder à la suppression définitive de l’ensemble de ses éléments sur la Plateforme.\n" +
                "                  </p>\n" +
                "                </article>  \n" +
                "              </ul>\n" +
                "            </section>\n");

        sb.append("  <h2>ARTICLE 7.  SERVICES </h2>\n" +
                "            <section>\n" +
                "                <ul class=\"list list-square\">\n" +
                "                    <li><h3>Article 7.1. Services offerts à tout Utilisateur  </h3></li>\n" +
                "                    <article>\n" +
                "                      <p>\n" +
                "                          En se créant un Compte conformément à l’article 7 des présentes, tout Utilisateur pourra accéder à l’ensemble des fonctionnalités gratuites de la Plateforme. \n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          Le principal Service proposé par LINKINNOV consiste à offrir à tout Utilisateur la possibilité de consulter les Profils et Annonces mises en ligne sur la Plateforme, d’échanger entre eux via un service de messagerie ainsi que de publier des Contenus via le mur d’actualité.\n" +
                "                      </p>\n" +
                "\n" +
                "                      <ul class=\"list list-none list-underlined\">\n" +
                "                          <li><h4>\n" +
                "                            Article 7.1.1. Recherche par le moteur de recherche\n" +
                "                          </h4></li>  \n" +
                "                          <article>\n" +
                "                            <p>\n" +
                "                                L’Utilisateur pourra utiliser le moteur de recherche pour rechercher un Profil, une Annonce, un groupe ou une production. \n" +
                "                            </p>\n" +
                "                            <p>\n" +
                "                                Dans ce cas, les résultats correspondant à sa recherche seront présentés par défaut à l’Utilisateur selon un critère de pertinence déterminé comme suit : sont affichés en priorité les résultats présentant dans leur titre, leur description, le plus de liens avec les mots clés utilisés par l’Utilisateur sur les champs référencés. \n" +
                "                            </p>\n" +
                "                          </article>\n" +
                "\n" +
                "                          <li><h4>\n" +
                "                              Article 7.1.2. Recherche filtrée\n" +
                "                          </h4></li>  \n" +
                "                          <article>\n" +
                "                            <p>\n" +
                "                                Lors de toute recherche sur la Plateforme, l’Utilisateur pourra affiner ses requêtes selon les filtres proposés. \n" +
                "                            </p>\n" +
                "                          </article>\n" +
                "\n" +
                "                          <li><h4>\n" +
                "                              Article 7.1.3. Messagerie \n" +
                "                          </h4></li>  \n" +
                "                          <article>\n" +
                "                            <p>\n" +
                "                                La Plateforme met à disposition des Utilisateurs une messagerie instantanée leur permettant d’échanger avec d’autres Utilisateurs. \n" +
                "                            </p>\n" +
                "                          </article>\n" +
                "\n" +
                "                          <li><h4>\n" +
                "                              Article 7.1.4. Groupes \n" +
                "                          </h4></li>  \n" +
                "                          <article>\n" +
                "                            <p>\n" +
                "                                Les Utilisateurs ont la possibilité de constitués des groupes restreints d’Utilisateurs publics ou privés.\n" +
                "                            </p>\n" +
                "                          </article>\n" +
                "                      </ul>\n" +
                "                    </article>\n" +
                "                </ul>\n" +
                "            </section>\n" +
                "\n" +
                "            <h2>ARTICLE 8.  OBLIGATION DES PARTIES </h2>\n" +
                "            <section>\n" +
                "                <ul class=\"list list-square\">\n" +
                "                    <li><h3>Article 8.1. Obligations générales des Utilisateurs </h3></li>\n" +
                "                    <article>\n" +
                "                      <p>\n" +
                "                          Dans le cadre de l’utilisation de la Plateforme, chaque Utilisateur s’engage à ne pas porter atteinte à l’ordre public et à se conformer aux lois et règlements en vigueur, à respecter les droits des tiers et les dispositions des présentes Conditions Générales d’Utilisation.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          Chaque Utilisateur a pour obligation de :\n" +
                "                      </p>\n" +
                "                      <ul class=\"list-dashed\">\n" +
                "                        <li>Se comporter de façon loyale et raisonnable à l’égard de LINKINNOV et des tiers ;</li> \n" +
                "                        <li>Être honnête et sincère dans les informations fournies à LINKINNOV et, le cas échéant aux tiers Utilisateurs ;</li>\n" +
                "                        <li>Utiliser la Plateforme conformément à son objet tel que décrit dans les présentes CGU ;</li>\n" +
                "                        <li>Ne pas détourner la finalité de la Plateforme pour commettre des crimes, délits ou contraventions réprimés par le Code pénal ou par toute autre loi ;</li>\n" +
                "                        <li>Respecter la vie privée des tiers et la confidentialité des échanges ;</li>\n" +
                "                        <li>Respecter les droits de propriété intellectuelle de LINKINNOV portant sur les éléments de la Plateforme et le cas échéant, les droits de propriété intellectuelle des autres Utilisateurs ; </li>\n" +
                "                        <li>Ne pas chercher à porter atteinte au sens des articles 323-1 et suivants du Code pénal aux systèmes de traitements automatisés de données mis en œuvre sur la Plateforme ;</li>\n" +
                "                        <li>Ne pas modifier les informations mises en ligne par LINKINNOV ou par un autre Utilisateur ; </li>\n" +
                "                        <li>Ne pas utiliser la Plateforme pour envoyer massivement des messages non sollicités (publicitaires ou autres) ;</li>\n" +
                "                        <li>Ne pas diffuser des données ayant pour effet de diminuer, de désorganiser, de ralentir ou d’interrompre le fonctionnement normal de la Plateforme. </li>\n" +
                "                      </ul>\n" +
                "\n" +
                "\n" +
                "                      <p>\n" +
                "                          Dans le respect des dispositions légales et réglementaires en vigueur et conformément à la loi du 29 juillet 1881 relative à la liberté de la presse, l’Utilisateur s’engage à ne pas diffuser de message ou information : \n" +
                "                      </p>\n" +
                "                      <ul class=\"list-dashed\">\n" +
                "                          <li>Constitutifs de dénigrement fautif visant LINKINNOV ou les Utilisateurs de la Plateforme ; \n" +
                "                          <li>Contraires à l’ordre public et aux bonnes mœurs. \n" +
                "                          <li>À caractère injurieux, diffamatoire, raciste, xénophobe, révisionniste ou portant atteinte à l'honneur ou à la réputation d'autrui ;\n" +
                "                          <li>Incitant à la discrimination, à la haine d'une personne ou d'un groupe de personnes à raison de leur origine ou de leur appartenance ou de leur non-appartenance à une ethnie, une nation, une race ou une religion déterminée ;\n" +
                "                          <li>menaçant une personne ou un groupe de personnes ;\n" +
                "                          <li>à caractère pédophile ;\n" +
                "                          <li>incitant à commettre un délit, un crime ou un acte de terrorisme ou faisant l'apologie des crimes de guerre ou des crimes contre l'humanité ;\n" +
                "                          <li>incitant au suicide ;\n" +
                "                          <li>permettant à des tiers de se procurer directement ou indirectement des logiciels piratés, des numéros de série de logiciels, des logiciels permettant des actes de piratage et d'intrusion dans les systèmes informatiques et de télécommunications, des virus et autres bombes logiques et d'une manière générale tout outil logiciel ou autre permettant de porter atteinte aux droits d'autrui et à la sécurité des personnes et des biens ;\n" +
                "                          <li>à caractère commercial (prospection, racolage, prostitution…).</li>\n" +
                "                      </ul>\n" +
                "                    </article>\n" +
                "\n" +
                "                    <li><h3>Article 8.2. Obligations des Utilisateurs liées à la fourniture d’une Prestation</h3></li>\n" +
                "                    <article>\n" +
                "                      <p>\n" +
                "                          Les Utilisateurs qui, suite à leur rencontre par l’intermédiaire de la Plateforme ou au dépôt d’une Annonce, seraient amenées à travailler ensemble pour la réalisation d’une Prestation sont invitées, préalablement à la fourniture de la Prestation à : \n" +
                "                      </p>\n" +
                "                      <ul class=\"list-dashed\">\n" +
                "                          <li>Se comporter de façon loyale et raisonnable à l’égard de LINKINNOV et des tiers ;S’assurer que la réalisation de la Prestation ne va pas à l’encontre de leur contrat de travail éventuel ;\n" +
                "                          <li>Réaliser et conclure en dehors de la Plateforme un Contrat de Prestation de Service en bonne et due forme régissant les conditions et modalités de la réalisation de la Prestation. </li> \n" +
                "                      </ul>\n" +
                "                    </article>\n" +
                "\n" +
                "                    <li><h3>Article 8.3. Obligations de LINKINNOV </h3></li>\n" +
                "                    <article>\n" +
                "                      <p>\n" +
                "                          L’obligation générale de LINKINNOV est une obligation de moyens. Il ne pèse sur LINKINNOV aucune obligation de résultat ou de moyens renforcée d’aucune sorte. \n" +
                "                      </p> \n" +
                "                      <p>\n" +
                "                          LINKINNOV s’engage à mettre tous les moyens en œuvre pour assurer une continuité d’accès et d’utilisation de la Plateforme 7 jours sur 7 et 24 heures sur 24.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          LINKINNOV attire toutefois l’attention des Utilisateurs sur le fait que les protocoles actuels de communication via Internet ne permettent pas d’assurer de manière certaine et continue la transmission des échanges électroniques (messages, documents, identité de l’émetteur ou du destinataire). \n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          Par ailleurs, conformément aux dispositions des articles L.111-7 et suivants du Code de la consommation, en tant qu’opérateur de plateforme en ligne, LINKINNOV s’engage à apporter une information claire transparente et loyale sur les modalités de son intervention, notamment au sein de l’article 5.2. des présentes.   \n" +
                "                      </p>\n" +
                "                    </article>\n" +
                "\n" +
                "                </ul>\n" +
                "            </section>\n" +
                "\n" +
                "            <h2>ARTICLE 9.  RESPONSABILITE </h2>\n" +
                "            <section>\n" +
                "                <ul class=\"list list-square\">\n" +
                "                    <li><h3>Article 9.1. Principes généraux </h3></li>\n" +
                "                    <article>\n" +
                "                      <p>\n" +
                "                          LINKINNOV décline toute responsabilité notamment :\n" +
                "                      </p>\n" +
                "                      <ul class=\"list-disc\">\n" +
                "                        <li>en cas d’impossibilité d’accéder temporairement à la Plateforme pour des opérations de maintenance technique ou d’actualisation des informations publiées. Les Utilisateurs reconnaissent que la responsabilité de LINKINNOV ne saurait être engagée en cas de dysfonctionnements ou d’interruptions desdits réseaux de transmission ; \n" +
                "                        <li>en cas d’attaques virales, intrusion illicite dans un système de traitement automatisé de données ;\n" +
                "                        <li>en cas d’utilisation anormale ou d’une exploitation illicite de la Plateforme par un Utilisateur ou un tiers ;\n" +
                "                        <li>relativement au contenu des sites internet tiers vers lesquels renvoient des liens hypertextes présents sur la Plateforme ;\n" +
                "                        <li>en cas de non-respect des présentes CGU imputable aux Utilisateurs ;\n" +
                "                        <li>en cas de retard ou d’inexécution de ses obligations, lorsque la cause du retard ou de l’inexécution est liée à un cas de force majeure telle qu’elle est définie à l’article 11 des présentes CGU ;\n" +
                "                        <li>en cas de cause étrangère non imputable à LINKINNOV ;\n" +
                "                        <li>en cas d’agissement illicite d’un Utilisateur, ou d’inexécution contractuelle dont un Utilisateur se serait rendu coupable ; \n" +
                "                        <li>liée à la formation ou à l’exécution d’un Contrat de Prestation de Service ;\n" +
                "                        <li>dans le cadre de tout litige lié à  l’exécution d’une Prestation par un Utilisateur. </li>\n" +
                "                      </ul>\n" +
                "\n" +
                "                      <p>\n" +
                "                          En cas d’utilisation anormale ou d’une exploitation illicite de la Plateforme, l’Utilisateur est alors seul responsable des dommages causés aux tiers et des conséquences des réclamations ou actions qui pourraient en découler. \n" +
                "                      </p>\n" +
                "                    </article>\n" +
                "\n" +
                "                    <li><h3>Article 9.2. Statut d’hébergeur  </h3></li>\n" +
                "                    <article>\n" +
                "                      <p>\n" +
                "                          Les Utilisateurs reconnaissent que LINKINNOV a la qualité d’hébergeur au sens de l’article 6 I 2° de la loi du 21 juin 2004 pour la confiance dans l’économie numérique dite LCEN.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          A ce titre, LINKINNOV se réserve la possibilité de retirer tout contenu qui lui aura été signalé et qu’elle considèrera comme manifestement illicite au sens de l’article 6 I 2° de la LCEN.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          La notification des contenus manifestement illicites par un Utilisateur ou tout autre tiers doit se faire via un lien « Signaler » disponible sur la Plateforme ou par courrier en recommandé avec avis de réception à LINKINNOV, 37 rue de Lyon 75012 PARIS.] \n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          Conformément à l’article 6 I 5° de la LCEN, la notification, pour être valide, doit reprendre les éléments suivants : \n" +
                "                      </p>\n" +
                "                      <ul class=\"list-dashed\">\n" +
                "                          <li>la date de la notification ;\n" +
                "                          <li>si le notifiant est une personne physique : ses nom, prénoms, profession, domicile, nationalité, date et lieu de naissance ; si le requérant est une personne morale : sa forme, sa dénomination, son siège social et l'organe qui la représente légalement ;\n" +
                "                          <li>les nom et domicile du destinataire ou, s'il s'agit d'une personne morale, sa dénomination et son siège social ;\n" +
                "                          <li>la description des faits litigieux et leur localisation précise ;\n" +
                "                          <li>les motifs pour lesquels le contenu doit être retiré, comprenant la mention des dispositions légales et des justifications de faits ;\n" +
                "                          <li>la copie de la correspondance adressée à l'auteur ou à l'éditeur des informations ou activités litigieuses demandant leur interruption, leur retrait ou leur modification, ou la justification de ce que l'auteur ou l'éditeur n'a pu être contacté.</li>\n" +
                "                      </ul>\n" +
                "                    </article>\n" +
                "\n" +
                "                    <li><h3>Article 9.3. Litiges entre Utilisateurs  </h3></li>\n" +
                "                    <article>\n" +
                "                      <p>\n" +
                "                          Tout litige survenant entre Utilisateurs devra être traité entre eux, en dehors de la Plateforme.\n" +
                "                      </p>\n" +
                "                    </article>  \n" +
                "                </ul>\n" +
                "            </section>  \n" +
                "\n" +
                "\n" +
                "            <h2>ARTICLE 10.  FORCE MAJEURE  </h2>\n" +
                "            <section>\n" +
                "                <p>\n" +
                "                    La responsabilité de LINKINNOV ne pourra pas être mise en œuvre si la non-exécution ou le retard dans l’exécution de l’une de ses obligations décrites dans les présentes CGU découle d’un cas de force majeure. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Il y a force majeure en matière contractuelle lorsqu’un évènement échappant au contrôle du débiteur, qui ne pouvait être raisonnablement prévu lors de la conclusion du contrat et dont les effets ne peuvent être évités par des mesures appropriées, empêche l’exécution de son obligation par le débiteur.  \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Si l’empêchement est temporaire, l’exécution de l’obligation est suspendue à moins que le retard qui en résulterait ne justifie la résolution du contrat. Si l’empêchement est définitif, le contrat est résolu de plein droit et les parties sont libérées de leurs obligations dans les conditions prévues aux articles 1351 et 1351-1 du Code civil. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    A ce titre, la responsabilité de LINKINNOV ne pourra pas être engagée notamment en cas d'attaque de pirates informatiques, d'indisponibilité de matériels, fournitures, pièces détachées, équipements personnels ou autres ; et d'interruption, de suspension, de réduction ou des dérangements de l'électricité ou autres ou toutes interruptions de réseaux de communications électroniques, ainsi qu’en cas de survenance de toute circonstance ou événement extérieur à la volonté de LINKINNOV intervenant postérieurement à la conclusion des CGU et en empêchant l’exécution dans des conditions normales. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Il est précisé que, dans une telle situation, l’Utilisateur ne peut réclamer le versement d’aucune indemnité et ne peut intenter aucun recours à l’encontre de LINKINNOV.\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    En cas de survenance d’un des évènements susvisés, LINKINNOV s’efforcera d’informer l’Utilisateur dès que possible.\n" +
                "                </p>\n" +
                "            </section>\n");

        sb.append("\n" +
                "            <h2>ARTICLE 11.  PROPRIETE INTELLECTUELLE </h2>\n" +
                "            <section>\n" +
                "                <ul class=\"list list-square\">\n" +
                "                    <li><h3>Article 11.1. Titularité des droits de propriété intellectuelle des Utilisateurs </h3></li>\n" +
                "                    <article>\n" +
                "                      <p>\n" +
                "                          Un fichier est susceptible de renfermer plusieurs droits : \n" +
                "                      </p>\n" +
                "                      <ul class=\"list-dashed\">\n" +
                "                          <li>Le droit des marques, brevets, et dessins et modèles.\n" +
                "                          <li>Les droits d’auteur (droit de la propriété intellectuelle) ;\n" +
                "                          <li>Les droits à l’image des personnes pouvant figurer sur des images ;\n" +
                "                          <li>Le droit à l’image des créations artistiques (tels par exemple, mais à titre non exhaustif les peintures, les sculptures, les images, les mobiliers, les objets de décorations etc..) pouvant figurer sur l’image et protégées par un droit d’auteur dès lors qu’elles en constituent le sujet principal ;</li> \n" +
                "                      </ul>\n" +
                "\n" +
                "                      <p>\n" +
                "                          A cet égard les Utilisateurs sont avertis qu’ils doivent, préalablement à toute Prestation s’assurer détenir l’intégralité des droits ou, le cas échéant, de se faire céder des droits qui y sont rattachés.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          Ils demeurent seuls responsables des actions en réponse, notamment en cas de contrefaçon ou toute autre atteinte aux droits des tiers, qui proviendraient d’un transfert de fichiers initié par eux.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          LINKINNOV s’engage à ne faire aucun commerce des fichiers publiés par  les Utilisateurs.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          En tout état de cause, LINKINNOV ne saurait être tenue responsable d’un acte de contrefaçon, compte tenu de sa simple qualité d’hébergeur des fichiers publiés par les Utilisateurs. \n" +
                "                      </p>\n" +
                "                    </article>\n" +
                "\n" +
                "\n" +
                "                    <li><h3>Article 11.2. Titularité des droits de propriété intellectuelle de LINKINNOV</h3></li>\n" +
                "                    <article>\n" +
                "                      <p>\n" +
                "                          L’Utilisateur reconnaît les droits de propriété intellectuelle de LINKINNOV sur la Plateforme, ses composantes et les contenus y afférent et renonce à contester ces droits sous quelle que forme que ce soit. \n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          Les marques, logos, slogans, graphismes, photographies, animations, vidéos, solutions logicielles et textes et tout autre contenu sur la Plateforme, à l’exception des Contenus publiés par les Utilisateurs, sont la propriété intellectuelle exclusive de LINKINNOV et ne peuvent être reproduits, utilisés ou représentés sans autorisation expresse sous peine de poursuites judiciaires.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          Toute représentation ou reproduction, totale ou partielle, de la Plateforme et de son contenu, par quel que procédé que ce soit, sans l’autorisation préalable expresse de LINKINNOV, est interdite et constituera une contrefaçon sanctionnée par les articles L.335-2 et suivants et les articles L.713-1 et suivants du Code de la Propriété Intellectuelle.\n" +
                "                      </p>\n" +
                "\n" +
                "                      <p>\n" +
                "                          En particulier, LINKINNOV interdit expressément : \n" +
                "                      </p>\n" +
                "                      <ul class=\"list-dashed\">\n" +
                "                          <li>L’extraction, par transfert permanent ou temporaire de la totalité ou d’une partie qualitativement ou quantitativement substantielle du contenu de sa base de données sur un autre support, par tout moyen et sous quelque forme que ce soit ;\n" +
                "                          <li>La réutilisation, par la mise à la disposition du public de la totalité ou d’une partie qualitativement ou quantitativement substantielle du contenu de la base, quelle qu’en soit la forme ; \n" +
                "                          <li>La reproduction, l’extraction ou la réutilisation, par tout moyen, y compris les méthodes assimilables au scrapping des contenus (photographies, description etc…) publiés par LINKINNOV ou par un Utilisateur. </li> \n" +
                "                      </ul>\n" +
                "\n" +
                "                      <p>\n" +
                "                          L’acceptation des présentes CGU vaut reconnaissance par les Utilisateurs des droits de propriété intellectuelle de LINKINNOV et engagement à les respecter.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          LINKINNOV accorde une licence personnelle, non-exclusive et non cessible aux Utilisateurs les autorisant à utiliser la Plateforme et les informations qu’elle contient conformément aux présentes CGU.\n" +
                "                      </p>\n" +
                "                      <p>\n" +
                "                          Toute autre exploitation de la Plateforme et de son contenu est exclue du domaine de la présente licence et ne pourra être effectuée sans l’autorisation préalable expresse de LINKINNOV.\n" +
                "                      </p>\n" +
                "                    </article>\n" +
                "                </ul>\n" +
                "            </section>\n" +
                "\n" +
                "\n" +
                "            <h2>ARTICLE 12.  PROTECTION DES DONNEES A CARACTERE PERSONNEL </h2>\n" +
                "            <section>\n" +
                "                <p>\n" +
                "                    Dans le cadre de l’exploitation de la Plateforme, LINKINNOV est susceptible de collecter des données à caractère personnel.\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Ces données sont nécessaires à la gestion de la Plateforme et si l’Utilisateur a expressément choisi cette option, lui envoyer des newsletters, sauf s’il ne souhaite plus recevoir de telles communication de la part de LINKINNOV.  Ces données seront conservées confidentiellement par LINKINNOV pour les besoins du contrat, de son exécution et dans le respect de la loi, pendant une durée de 3 ans à compter de la fin de la relation commerciale si vous êtes client ou à compter de votre dernier contact si vous n’êtes pas encore client. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Les données peuvent être communiquées en tout ou partie aux prestataires de services de LINKINNOV.  \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    A ce titre, l’Utilisateur est invité à consulter la Politique de confidentialité de LINKINNOV qui lui donnera l’ensemble des informations relatives à la protection des données à caractère personnel et aux traitements effectués via la Plateforme. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Conformément à la loi n°78-17 du 6 janvier 1978 relative à l’informatique aux fichiers et aux libertés modifiée et au Règlement (UE) 2016/679 du Parlement européen et du Conseil du 27 avril 2016 relatif à la protection des personnes physiques à l’égard du traitement des données à caractère personnel et à la libre circulation de ces données, et abrogeant la directive 95/46/CE (Règlement général sur la protection des données, dit RGPD), LINKINNOV assure la mise en œuvre des droits des personnes concernées. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Il est rappelé que l’Utilisateur dont les données à caractère personnel sont traitées bénéficie des droits d’accès, de rectification, de mise à jour, de portabilité et d’effacement des informations qui le concernent, ainsi qu’un droit à la limitation du traitement conformément aux articles 49, 50, 51, 53 et 55 de la Loi Informatique et Libertés et aux dispositions des articles 15, 16, 17 et 18 du RGPD. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Conformément aux dispositions de l’article 56 de la Loi Informatique et Libertés et à l’article 21 du RGPD, l’Utilisateur peut également, pour des motifs légitimes, s’opposer au traitement des données le concernant, sans motif et sans frais. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    L’Utilisateur peut exercer ses droits en adressant un courrier électronique à l’adresse connect@linkinnov.com ou en envoyant un courrier à LINKINNOV, 37 rue de Lyon 75012 PARIS. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Enfin, l’Utilisateur peut également introduire une réclamation auprès des autorités de contrôle et notamment de la CNIL (<a href=\"https://www.cnil.fr/fr/plaintes\">https://www.cnil.fr/fr/plaintes</a>).\n" +
                "                </p>\n" +
                "            </section>\n" +
                "\n" +
                "\n" +
                "            <h2>ARTICLE 13.  SERVICE CLIENTS </h2>\n" +
                "            <section>\n" +
                "                <p>\n" +
                "                    Toute question ou réclamation concernant l’utilisation ou le fonctionnement de la Plateforme peut être formulée selon les modalités suivantes : \n" +
                "                </p>\n" +
                "                <ul class=\"list-disc\">\n" +
                "                    <li>Par courrier électronique à l’adresse connect@linkinnov.com ;\n" +
                "                    <li>Par courrier à LINKINNOV, 37 rue de Lyon 75012 PARIS ;\n" +
                "                    </li>\n" +
                "                </ul>\n" +
                "            </section>  \n" +
                "\n" +
                "\n" +
                "            <h2>ARTICLE 14.  VALIDITE DES CGU </h2>\n" +
                "            <section>\n" +
                "                <p>\n" +
                "                    Si l’une quelconque des stipulations des présentes CGU venait à être déclarée nulle au regard d’une disposition législative ou réglementaire en vigueur et/ou d’une décision de justice ayant autorité de la chose jugée, elle sera réputée non écrite mais n’affectera en rien la validité des autres clauses qui demeureront pleinement applicables.\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Une telle modification ou décision n’autorise en aucun cas les Utilisateurs à méconnaitre les présentes Conditions Générales d’Utilisation.\n" +
                "                </p>\n" +
                "            </section>\n" +
                "            \n" +
                "\n" +
                "            <h2>ARTICLE 15.  MODIFICATION DES CGU   </h2>\n" +
                "            <section>\n" +
                "                <p>\n" +
                "                    Les présentes CGU s’appliquent à tout Utilisateur navigant sur la Plateforme. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Les CGU pourront être modifiées et mises à jour par LINKINNOV à tout moment, notamment pour s’adapter à l’évolution législative ou réglementaire. Toute modification sera portée à l’attention des Utilisateurs par voie de mail et/ou directement sur la plateforme préalablement à leur entrée en vigueur. Dans l’hypothèse où l’Utilisateur ne souhaiterait pas consentir au changement, celui-ci est invité à supprimer son Compte.\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Les CGU applicables sont celles en vigueur au moment de la navigation sur la Plateforme. \n" +
                "                </p>\n" +
                "            </section>\n" +
                "\n" +
                "\n" +
                "            <h2>ARTICLE 16.  DISPOSITIONS GENERALES </h2>\n" +
                "            <section>\n" +
                "                <p>\n" +
                "                    Le fait que l'une des Parties n'ait pas exigé l'application d'une clause quelconque des présentes CGU, que ce soit de façon permanente ou temporaire, ne pourra en aucun cas être considéré comme une renonciation à ladite clause.\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    En cas de difficulté d’interprétation entre l’un quelconque des titres figurant en tête des clauses, et l’une quelconque de celles-ci, les titres seront déclarés inexistants.\n" +
                "                </p>\n" +
                "            </section>  \n" +
                "\n" +
                "\n" +
                "            <h2>ARTICLE 17.  COMPETENCE ET DROIT APPLICABLE  </h2>\n" +
                "            <section>\n" +
                "                <p>\n" +
                "                    LES PRESENTES CGU AINSI QUE LES RELATIONS ENTRE L’UTILISATEUR ET LINKINNOV SONT REGIES PAR LE DROIT FRANÇAIS. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    En cas de différend survenant entre LINKINNOV et un Utilisateur au sujet de l’interprétation, de l’exécution ou de la résiliation des présentes, les Parties s’efforceront de le régler à l’amiable. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Dans un tel cas de figure, l’Utilisateur est tout d’abord invité à contacter le service de médiation de LINKINNOV à l’adresse connect@linkinnov.com .\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Si aucun accord n’est trouvé, il sera alors proposé une procédure de médiation facultative, menée dans un esprit de loyauté et de bonne foi en vue de parvenir à un accord amiable lors de la survenance de tout conflit relatif au présent contrat, y compris portant sur sa validité.\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    Pour enclencher cette médiation, l’Utilisateur consommateur peut contacter le médiateur : claims@linkinnov.com.\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    La partie souhaitant mettre en œuvre le processus de médiation devra préalablement en informer l’autre partie par lettre recommandée avec accusé de réception en indiquant les éléments du conflit. \n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    La médiation ne présentant pas un caractère obligatoire, l’Utilisateur consommateur ou LINKINNOV peut à tout moment se retirer du processus.\n" +
                "                </p>\n" +
                "                <p class=\" small-caps\">\n" +
                "                    DANS L’HYPOTHESE OU LA MEDIATION ECHOUERAIT OU NE SERAIT PAS ENVISAGEE, LE LITIGE AYANT PU DONNER LIEU A UNE MEDIATION SERA CONFIE AU TRIBUNAL COMPETENT. \n" +
                "                </p>\n" +
                "            </section>  \n" +
                "        </div>\n" +
                "    </div>");


        DynamicPageDTO cguDynamicPageDTO = new DynamicPageDTO();
        cguDynamicPageDTO.setId("uuid-cgu");
        cguDynamicPageDTO.setContent(sb.toString());
        save(cguDynamicPageDTO);

        StringBuilder pdcpSb = new StringBuilder();
        pdcpSb.append("<style>\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 100;\n" +
                "    src: local('Montserrat Thin'), local('Montserrat-Thin'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUQjIg1_i6t8kCHKm45_QpRxC7mw9c.woff2) format('woff2');\n" +
                "    unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 100;\n" +
                "    src: local('Montserrat Thin'), local('Montserrat-Thin'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUQjIg1_i6t8kCHKm45_QpRzS7mw9c.woff2) format('woff2');\n" +
                "    unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "  }\n" +
                "\n" +
                "  /* vietnamese */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 100;\n" +
                "    src: local('Montserrat Thin'), local('Montserrat-Thin'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUQjIg1_i6t8kCHKm45_QpRxi7mw9c.woff2) format('woff2');\n" +
                "    unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "  }\n" +
                "\n" +
                "  /* latin-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 100;\n" +
                "    src: local('Montserrat Thin'), local('Montserrat-Thin'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUQjIg1_i6t8kCHKm45_QpRxy7mw9c.woff2) format('woff2');\n" +
                "    unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "  }\n" +
                "\n" +
                "  /* latin */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 100;\n" +
                "    src: local('Montserrat Thin'), local('Montserrat-Thin'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUQjIg1_i6t8kCHKm45_QpRyS7m.woff2) format('woff2');\n" +
                "    unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 200;\n" +
                "    src: local('Montserrat ExtraLight'), local('Montserrat-ExtraLight'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_aZA3gTD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 200;\n" +
                "    src: local('Montserrat ExtraLight'), local('Montserrat-ExtraLight'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_aZA3g3D_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "  }\n" +
                "\n" +
                "  /* vietnamese */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 200;\n" +
                "    src: local('Montserrat ExtraLight'), local('Montserrat-ExtraLight'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_aZA3gbD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "  }\n" +
                "\n" +
                "  /* latin-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 200;\n" +
                "    src: local('Montserrat ExtraLight'), local('Montserrat-ExtraLight'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_aZA3gfD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "  }\n" +
                "\n" +
                "  /* latin */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 200;\n" +
                "    src: local('Montserrat ExtraLight'), local('Montserrat-ExtraLight'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_aZA3gnD_g.woff2) format('woff2');\n" +
                "    unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 300;\n" +
                "    src: local('Montserrat Light'), local('Montserrat-Light'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_cJD3gTD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 300;\n" +
                "    src: local('Montserrat Light'), local('Montserrat-Light'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_cJD3g3D_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "  }\n" +
                "\n" +
                "  /* vietnamese */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 300;\n" +
                "    src: local('Montserrat Light'), local('Montserrat-Light'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_cJD3gbD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "  }\n" +
                "\n" +
                "  /* latin-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 300;\n" +
                "    src: local('Montserrat Light'), local('Montserrat-Light'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_cJD3gfD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "  }\n" +
                "\n" +
                "  /* latin */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 300;\n" +
                "    src: local('Montserrat Light'), local('Montserrat-Light'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_cJD3gnD_g.woff2) format('woff2');\n" +
                "    unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 400;\n" +
                "    src: local('Montserrat Regular'), local('Montserrat-Regular'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUSjIg1_i6t8kCHKm459WRhyzbi.woff2) format('woff2');\n" +
                "    unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 400;\n" +
                "    src: local('Montserrat Regular'), local('Montserrat-Regular'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUSjIg1_i6t8kCHKm459W1hyzbi.woff2) format('woff2');\n" +
                "    unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "  }\n" +
                "\n" +
                "  /* vietnamese */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 400;\n" +
                "    src: local('Montserrat Regular'), local('Montserrat-Regular'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUSjIg1_i6t8kCHKm459WZhyzbi.woff2) format('woff2');\n" +
                "    unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "  }\n" +
                "\n" +
                "  /* latin-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 400;\n" +
                "    src: local('Montserrat Regular'), local('Montserrat-Regular'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUSjIg1_i6t8kCHKm459Wdhyzbi.woff2) format('woff2');\n" +
                "    unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "  }\n" +
                "\n" +
                "  /* latin */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 400;\n" +
                "    src: local('Montserrat Regular'), local('Montserrat-Regular'), url(https://fonts.gstatic.com/s/montserrat/v14/JTUSjIg1_i6t8kCHKm459Wlhyw.woff2) format('woff2');\n" +
                "    unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 500;\n" +
                "    src: local('Montserrat Medium'), local('Montserrat-Medium'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_ZpC3gTD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 500;\n" +
                "    src: local('Montserrat Medium'), local('Montserrat-Medium'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_ZpC3g3D_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "  }\n" +
                "\n" +
                "  /* vietnamese */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 500;\n" +
                "    src: local('Montserrat Medium'), local('Montserrat-Medium'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_ZpC3gbD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "  }\n" +
                "\n" +
                "  /* latin-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 500;\n" +
                "    src: local('Montserrat Medium'), local('Montserrat-Medium'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_ZpC3gfD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "  }\n" +
                "\n" +
                "  /* latin */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 500;\n" +
                "    src: local('Montserrat Medium'), local('Montserrat-Medium'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_ZpC3gnD_g.woff2) format('woff2');\n" +
                "    unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 600;\n" +
                "    src: local('Montserrat SemiBold'), local('Montserrat-SemiBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_bZF3gTD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 600;\n" +
                "    src: local('Montserrat SemiBold'), local('Montserrat-SemiBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_bZF3g3D_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "  }\n" +
                "\n" +
                "  /* vietnamese */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 600;\n" +
                "    src: local('Montserrat SemiBold'), local('Montserrat-SemiBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_bZF3gbD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "  }\n" +
                "\n" +
                "  /* latin-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 600;\n" +
                "    src: local('Montserrat SemiBold'), local('Montserrat-SemiBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_bZF3gfD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "  }\n" +
                "\n" +
                "  /* latin */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 600;\n" +
                "    src: local('Montserrat SemiBold'), local('Montserrat-SemiBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_bZF3gnD_g.woff2) format('woff2');\n" +
                "    unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 700;\n" +
                "    src: local('Montserrat Bold'), local('Montserrat-Bold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_dJE3gTD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 700;\n" +
                "    src: local('Montserrat Bold'), local('Montserrat-Bold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_dJE3g3D_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "  }\n" +
                "\n" +
                "  /* vietnamese */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 700;\n" +
                "    src: local('Montserrat Bold'), local('Montserrat-Bold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_dJE3gbD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "  }\n" +
                "\n" +
                "  /* latin-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 700;\n" +
                "    src: local('Montserrat Bold'), local('Montserrat-Bold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_dJE3gfD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "  }\n" +
                "\n" +
                "  /* latin */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 700;\n" +
                "    src: local('Montserrat Bold'), local('Montserrat-Bold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_dJE3gnD_g.woff2) format('woff2');\n" +
                "    unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 800;\n" +
                "    src: local('Montserrat ExtraBold'), local('Montserrat-ExtraBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_c5H3gTD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 800;\n" +
                "    src: local('Montserrat ExtraBold'), local('Montserrat-ExtraBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_c5H3g3D_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "  }\n" +
                "\n" +
                "  /* vietnamese */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 800;\n" +
                "    src: local('Montserrat ExtraBold'), local('Montserrat-ExtraBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_c5H3gbD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "  }\n" +
                "\n" +
                "  /* latin-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 800;\n" +
                "    src: local('Montserrat ExtraBold'), local('Montserrat-ExtraBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_c5H3gfD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "  }\n" +
                "\n" +
                "  /* latin */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 800;\n" +
                "    src: local('Montserrat ExtraBold'), local('Montserrat-ExtraBold'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_c5H3gnD_g.woff2) format('woff2');\n" +
                "    unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 900;\n" +
                "    src: local('Montserrat Black'), local('Montserrat-Black'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_epG3gTD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;\n" +
                "  }\n" +
                "\n" +
                "  /* cyrillic */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 900;\n" +
                "    src: local('Montserrat Black'), local('Montserrat-Black'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_epG3g3D_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;\n" +
                "  }\n" +
                "\n" +
                "  /* vietnamese */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 900;\n" +
                "    src: local('Montserrat Black'), local('Montserrat-Black'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_epG3gbD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0102-0103, U+0110-0111, U+1EA0-1EF9, U+20AB;\n" +
                "  }\n" +
                "\n" +
                "  /* latin-ext */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 900;\n" +
                "    src: local('Montserrat Black'), local('Montserrat-Black'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_epG3gfD_u50.woff2) format('woff2');\n" +
                "    unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;\n" +
                "  }\n" +
                "\n" +
                "  /* latin */\n" +
                "  @font-face {\n" +
                "    font-family: 'Montserrat';\n" +
                "    font-style: normal;\n" +
                "    font-weight: 900;\n" +
                "    src: local('Montserrat Black'), local('Montserrat-Black'), url(https://fonts.gstatic.com/s/montserrat/v14/JTURjIg1_i6t8kCHKm45_epG3gnD_g.woff2) format('woff2');\n" +
                "    unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;\n" +
                "  }\n" +
                "</style>\n" +
                "<style>\n" +
                "  .body-CGU {\n" +
                "    font-family: \"Montserrat\";\n" +
                "    font-size: 12px;\n" +
                "    max-width: 1280px;\n" +
                "    background: #fff;\n" +
                "    margin: auto;\n" +
                "    line-height: 18px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU {\n" +
                "    padding: 10px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU a {\n" +
                "    color: #006DB6;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU h1 {\n" +
                "    text-align: center;\n" +
                "    font-size: 42px;\n" +
                "    margin-bottom: 15px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU h2 {\n" +
                "    margin-top: 40px;\n" +
                "    font-size: 15px;\n" +
                "    background-color: #EDEDED;\n" +
                "    padding: 2px 5px;\n" +
                "    letter-spacing: 1px;\n" +
                "    margin-bottom: 15px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU h3 {\n" +
                "    font-size: 14px;\n" +
                "    margin-bottom: 10px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU h4 {\n" +
                "    font-size: 13px;\n" +
                "    margin-bottom: 10px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU article {\n" +
                "    margin-bottom: 20px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU p {\n" +
                "    margin-block-start: 13px;\n" +
                "    margin-block-end: 13px;\n" +
                "    margin-inline-start: 0px;\n" +
                "    margin-inline-end: 0px;\n" +
                "    padding-bottom: 0;\n" +
                "    line-height: 18px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .btn-text {\n" +
                "    border: none;\n" +
                "    cursor: pointer;\n" +
                "    background-color: transparent;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .fnt-14px {\n" +
                "    font-size: 14px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .small-caps {\n" +
                "    font-variant: small-caps;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .logo-lkv {\n" +
                "    width: 258px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .header-CGU {\n" +
                "    display: block;\n" +
                "    padding: 120px 0;\n" +
                "    width: 100%;\n" +
                "    max-height: 100vh;\n" +
                "  }\n" +
                "\n" +
                "  .header-CGU img {\n" +
                "    display: block;\n" +
                "    margin: auto;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .important-blc {\n" +
                "    font-weight: bold;\n" +
                "    font-size: 13px;\n" +
                "    padding: 10px 20px;\n" +
                "    border: 1px #f00 solid;\n" +
                "    text-align: center;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .grey-blc-title {\n" +
                "    text-decoration: underline;\n" +
                "    color: #009;\n" +
                "    font-weight: bold;\n" +
                "    margin: 3px 0 5px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .grey-blc {\n" +
                "    border: solid 1px #000;\n" +
                "    background: #eee;\n" +
                "    padding: 0 15px;\n" +
                "    margin-bottom: 20px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .table-grey {\n" +
                "    background: #eee;\n" +
                "    border-collapse: collapse;\n" +
                "    font-size: 12px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .table-grey td {\n" +
                "    border: 1px solid #000;\n" +
                "    padding: 5px 10px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU ul {\n" +
                "    padding-left: 40px;\n" +
                "    list-style-type: disc;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU ul li {\n" +
                "    margin-bottom: 6px;\n" +
                "    text-align: left;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .list>li {\n" +
                "    margin-bottom: 15px;\n" +
                "  }\n" +
                "\n" +
                "\n" +
                "  .body-CGU .main-CGU .list.list-min-margin li {\n" +
                "    margin-bottom: 3px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .list-square {\n" +
                "    list-style-type: square;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .list-disc {\n" +
                "    list-style-type: disc;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .list-none {\n" +
                "    list-style-type: none;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .list-dashed {\n" +
                "    list-style-type: none;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .list-dashed>li {\n" +
                "    text-indent: -26px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .list-dashed>li:before {\n" +
                "    content: '-';\n" +
                "    padding-right: 20px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .list-red-square {\n" +
                "    list-style-type: none;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .list-red-square>li {\n" +
                "    text-indent: -26px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .list-red-square>li:before {\n" +
                "    content: '';\n" +
                "    display: inline-block;\n" +
                "    width: 7px;\n" +
                "    height: 7px;\n" +
                "    background-color: #f00;\n" +
                "    margin-right: 20px;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .list-underlined li {\n" +
                "    text-decoration: underline;\n" +
                "  }\n" +
                "\n" +
                "  .body-CGU .main-CGU .list h4 {\n" +
                "    font-weight: normal;\n" +
                "    font-style: italic;\n" +
                "  }\n" +
                "</style>");
        pdcpSb.append("<div class=\"body-CGU\">\n" +
                "  <div class=\"header-CGU\">\n" +
                "    <h1>Politique de confidentialité</h1>\n" +
                "    <img class=\"logo-lkv\" alt=\"logo-linkinnov\"\n" +
                "      src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAA88AAAKUCAYAAAA6tQpDAAAACXBIWXMAACE4AAAhOAFFljFgAAAFIGlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS42LWMxNDUgNzkuMTYzNDk5LCAyMDE4LzA4LzEzLTE2OjQwOjIyICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RFdnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ0MgMjAxOSAoTWFjaW50b3NoKSIgeG1wOkNyZWF0ZURhdGU9IjIwMTktMDEtMjFUMTE6NDE6MjYrMDE6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDE5LTAzLTE1VDExOjQ3OjA1KzAxOjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE5LTAzLTE1VDExOjQ3OjA1KzAxOjAwIiBkYzpmb3JtYXQ9ImltYWdlL3BuZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgcGhvdG9zaG9wOklDQ1Byb2ZpbGU9InNSR0IgSUVDNjE5NjYtMi4xIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOmQyYjM1ZDg1LTRk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' />\n" +
                "  </div>\n" +
                "\n" +
                "  <div class=\"main-CGU\">\n" +
                "    <h2>PREAMBULE</h2>\n" +
                "    <article>\n" +
                "      <p>Cette Politique de confidentialité s’adresse à vous, en votre qualité d’utilisateur de la Plateforme LINKINNOV\n" +
                "        et a pour objectif de vous informer de la manière dont vos informations personnelles peuvent le cas échéant être\n" +
                "        collectées et traitées par LINKINNOV.</p>\n" +
                "      <p>Le respect de votre vie privée et de vos données à caractère personnel est pour nous une priorité et nous nous\n" +
                "        engageons à traiter vos données dans le plus strict respect de la loi Informatique et Libertés du 6 janvier 1978\n" +
                "        modifiée et du règlement (UE) général sur la protection des données du 27 avril 2016, désigné ci-après « RGPD ».\n" +
                "      </p>\n" +
                "      <p>En tout état de cause, nous nous engageons à respecter les deux principes essentiels suivants : </p>\n" +
                "      <ul class=\"list list-red-square\">\n" +
                "        <li>Vous restez maître de vos données à caractère personnel ;</li>\n" +
                "        <li>Vos données sont traitées de manière transparente, confidentielle et sécurisée. </li>\n" +
                "      </ul>\n" +
                "\n" +
                "    </article>\n" +
                "\n" +
                "    <h2>ARTICLE 1. DEFINITIONS </h2>\n" +
                "    <article>\n" +
                "      <p>Les termes, mentionnés ci-dessous, ont dans la présente Politique de confidentialité, la signification suivante\n" +
                "        :</p>\n" +
                "      <ul class=\"list\">\n" +
                "        <li><strong>« Académique »</strong> : désigne toute personne physique accédant à la Plateforme et procédant à la\n" +
                "          création d’un Profil en vue de mettre en ligne un Contenu. </li>\n" +
                "        <li><strong>« Annonce »</strong> : désigne toute offre publiée par un Utilisateur sur la Plateforme en vue de la\n" +
                "          réalisation d’une Prestation.</li>\n" +
                "        <li><strong>« Annonceur »</strong> : désigne l’Utilisateur sur la Plateforme qui publie une Annonce.</li>\n" +
                "        <li><strong>« Compte »</strong> : désigne l’interface hébergée sur la Plateforme dans laquelle est regroupé\n" +
                "          l’ensemble des données fournies par l’Utilisateur. L’accès au Compte se fait grâce aux Identifiants.</li>\n" +
                "        <li><strong>« Conditions Générales d’Utilisation »</strong> ou <strong>« CGU » </strong> désigne les conditions\n" +
                "          contractuelles visant à encadrer l’utilisation de la Plateforme par l’Utilisateur.</li>\n" +
                "        <li><strong>« Destinataire »</strong> : désigne toute personne habilitée à obtenir la communication de données\n" +
                "          enregistrées dans un fichier ou un traitement en raison de ses fonctions.</li>\n" +
                "        <li><strong>« Données »</strong> : désigne l’ensemble des informations, textes, logos, marques, animations,\n" +
                "          dessins et modèles, photographies, Données Personnelles, liens hypertextes, et de façon générale tous les\n" +
                "          éléments et contenus mis en ligne par les Utilisateurs sur la Plateforme.</li>\n" +
                "        <li><strong>« Données Personnelles »</strong> : désigne toute information se rapportant à une personne physique\n" +
                "          qui peut être identifiée, directement ou indirectement, notamment par référence à un identifiant, tel qu'un\n" +
                "          nom, un numéro d'identification, des données de localisation, un identifiant en ligne, ou à un ou plusieurs\n" +
                "          éléments spécifiques propres à son identité physique, physiologique, génétique, psychique, économique,\n" +
                "          culturelle ou sociale.</li>\n" +
                "        <li><strong>« LINKINNOV »</strong> : désigne la société LINKINNOV, société par actions simplifiée au capital de\n" +
                "          10 000 euros, immatriculée au RCS de Paris sous le numéro 854 060 399, dont le siège social est situé 37 rue\n" +
                "          de Lyon 75012 PARIS, qui édite et exploite la Plateforme.</li>\n" +
                "        <li><strong>« Industriel »</strong> : désigne toute personne morale professionnelle accédant à la Plateforme\n" +
                "          notamment en vue de bénéficier d’une Prestation. </li>\n" +
                "        <li><strong>« Loi IEL »</strong> : désigne la loi n°78-17 du 6 janvier 1978 relative à l’informatique, aux\n" +
                "          fichiers et aux libertés telle que modifiée par toute disposition légale ou réglementaire en vigueur au jour\n" +
                "          de consultation de la présente Politique de confidentialité.</li>\n" +
                "        <li><strong>« Plateforme »</strong> : désigne la plateforme éditée par LINKINNOV ainsi que toutes ses\n" +
                "          composantes graphiques, sonores, visuelles, logicielles, et textuelles. La Plateforme est accessible via le\n" +
                "          site internet <a href=\"https://www.linkinnov.com/\">https://www.linkinnov.com/</a> &nbsp <a\n" +
                "            href=\"https://www.linkinnov.com/\"> et de ses sous-domaines.</a></li>\n" +
                "        <li><strong>« Prestation »</strong> : désigne toute prestation intellectuelle pouvant être proposée en réponse à\n" +
                "          une Annonce, via la Plateforme. </li>\n" +
                "        <li><strong>« Responsable du Traitement »</strong> désigne la personne qui, seule ou conjointement avec une\n" +
                "          autre, détermine les moyens et finalité d’un Traitement de Données Personnelles.</li>\n" +
                "        <li><strong>« RGPD »</strong> : désigne le règlement (UE) 2016/679 du Parlement européen et du Conseil du 27\n" +
                "          avril 2016 relatif à la protection des personnes physiques à l'égard du traitement des données à caractère\n" +
                "          personnel et à la libre circulation de ces données, et abrogeant la directive 95/46/CE.</li>\n" +
                "        <li><strong>« Services »</strong> : désigne l’ensemble des services proposés par LINKINNOV aux Utilisateurs via\n" +
                "          la Plateforme.</li>\n" +
                "        <li><strong>« Sous-Traitant »</strong> : désigne la personne traitant des Données Personnelles pour le compte du\n" +
                "          Responsable du Traitement, qui agit sous son autorité et sur instruction de celui-ci.</li>\n" +
                "        <li><strong>« Traitement »</strong> : désigne, toute opération, ou ensemble d’opérations, portant sur des\n" +
                "          Données Personnelles, quel que soit le procédé utilisé (collecte, enregistrement, organisation, conservation,\n" +
                "          adaptation, modification, extraction, consultation, utilisation, communication par transmission diffusion ou\n" +
                "          toute autre forme de mise à disposition, rapprochement ou interconnexion, verrouillage, effacement ou\n" +
                "          destruction, ...)</li>\n" +
                "        <li><strong>« Utilisateur »</strong> : désigne toute personne qui accède et navigue sur la Plateforme, qu’il\n" +
                "          soit Académique, Industriel ou autre acteur de l’innovation.</li>\n" +
                "\n" +
                "\n" +
                "      </ul>\n" +
                "    </article>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    <h2>ARTICLE 2. IDENTITE & COORDONNEES DU RESPONSABLE DE TRAITEMENT</h2>\n" +
                "    <article>\n" +
                "      <p>\n" +
                "        Le Responsable du Traitement de vos Données Personnelles est la société LINKINNOV, société par actions\n" +
                "        simplifiée (RCS Paris 854 060 399).\n" +
                "      </p>\n" +
                "      <p class=\"grey-blc-title\">En savoir plus et nos coordonnées</p>\n" +
                "      <section class=\"grey-blc\">\n" +
                "        <p>\n" +
                "          LINKINNOV est une société par actions simplifiée au capital de 10.000 euros, immatriculée au registre du\n" +
                "          commerce et des sociétés de Paris sous le numéro 854 060 399, dont le siège social est situé 37, rue de Lyon –\n" +
                "          75012 PARIS.\n" +
                "        </p>\n" +
                "        <p>\n" +
                "          Pour toute question relative à la gestion et à l’utilisation faite de vos Données Personnelles, vous pouvez\n" +
                "          nous joindre :\n" +
                "        </p>\n" +
                "        <ul class=\"list list-red-square\">\n" +
                "          <li>Soit par email à connect@linkinnov.com</li>\n" +
                "          <li>Soit par courrier à LINKINNOV 37, rue de Lyon – 75012 Paris </li>\n" +
                "        </ul>\n" +
                "      </section>\n" +
                "    </article>\n" +
                "\n" +
                "    <h2>ARTICLE 3. REGISTRE DES ACTIVITES DE TRAITEMENT</h2>\n" +
                "    <article>\n" +
                "      <p>\n" +
                "        Conformément à l’article 30 du RGPD, le Traitement de vos Données Personnelles fait l’objet de fiches de\n" +
                "        Traitements qui sont insérées dans notre registre des activités de Traitement.\n" +
                "      </p>\n" +
                "    </article>");

        pdcpSb.append(" <h2>ARTICLE 4. COLLECTE & ORIGINE DES DONNEES PERSONNELLES </h2>\n" +
                "    <article>\n" +
                "      <p>\n" +
                "        L’ensemble des données vous concernant sont collectées soit directement auprès de vous (informations\n" +
                "        communiquées lors de votre inscription sur la Plateforme ou de la commande de Prestations, soit indirectement à\n" +
                "        l’occasion de votre visite sur notre Plateforme (données de connexion et de navigation).\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        Dans tous les cas, vos Données Personnelles sont collectées et traitées uniquement pour répondre à vos demandes\n" +
                "        de renseignement, gérer votre inscription sur la Plateforme et assurer la fourniture des différents Services.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        Le détail de la manière dont vos Données Personnelles sont collectées et traitées par LINKINNOV est fourni dans\n" +
                "        la présente Politique de Confidentialité.\n" +
                "      </p>\n" +
                "\n" +
                "      <p class=\"grey-blc-title\">Plus de détail</p>\n" +
                "      <div class=\"grey-blc\">\n" +
                "        <p>\n" +
                "          Lorsque cela est nécessaire, nous nous engageons, selon les cas, à recueillir votre consentement et/ou à vous\n" +
                "          permettre de vous opposer à l’utilisation de vos Données Personnelles pour certaines finalités, comme par\n" +
                "          exemple, la possibilité de déposer des cookies tiers sur votre terminal, à des fins de mesure d’audience de\n" +
                "          notre Plateforme.\n" +
                "        </p>\n" +
                "      </div>\n" +
                "\n" +
                "    </article>\n" +
                "\n" +
                "    <h2>ARTICLE 5. FINALITES & BASE LEGALE DES TRAITEMENTS</h2>\n" +
                "    <article>\n" +
                "      <p>Vos Données Personnelles sont collectées et traitées pour :</p>\n" +
                "      <ul class=\"list list-red-square\">\n" +
                "        <li>La gestion de votre inscription et le suivi de votre Compte </li>\n" +
                "        <p class=\"grey-blc-title\">Base légale</p>\n" +
                "        <div class=\"grey-blc\">\n" +
                "          <p>\n" +
                "            <u>Base légale : </u>\n" +
                "          </p>\n" +
                "          <ul class=\"list list-dashed\">\n" +
                "            <li>Contractuelle, le traitement est nécessaire à l’exécution d’un contrat» et Conditions Générales\n" +
                "              d’Utilisation pour l’ensemble des Utilisateurs) ou de mesures précontractuelles.</li>\n" +
                "          </ul>\n" +
                "        </div>\n" +
                "\n" +
                "        <li>Répondre à vos demandes d’informations sur nos Services</li>\n" +
                "        <p class=\"grey-blc-title\">Base légale</p>\n" +
                "        <div class=\"grey-blc\">\n" +
                "          <p>\n" +
                "            <u>Base légale : </u>\n" +
                "          </p>\n" +
                "          <ul class=\"list list-dashed\">\n" +
                "            <li>Votre consentement </li>\n" +
                "          </ul>\n" +
                "        </div>\n" +
                "\n" +
                "        <li>Gérer et répondre à vos demandes d’exercice de droits « Informatique et libertés »</li>\n" +
                "        <p class=\"grey-blc-title\">Base légale</p>\n" +
                "        <div class=\"grey-blc\">\n" +
                "          <p>\n" +
                "            <u>Base légale : </u>\n" +
                "          </p>\n" +
                "          <ul class=\"list list-dashed\">\n" +
                "            <li>Obligation légale (RGPD et Loi Informatique & Libertés)</li>\n" +
                "          </ul>\n" +
                "        </div>\n" +
                "\n" +
                "        <li> Assurer le bon fonctionnement et l’amélioration permanente de notre Plateforme et de ses fonctionnalités\n" +
                "        </li>\n" +
                "        <p class=\"grey-blc-title\">Base légale</p>\n" +
                "        <div class=\"grey-blc\">\n" +
                "          <p>\n" +
                "            <u>Base légale : </u>\n" +
                "          </p>\n" +
                "          <ul class=\"list list-dashed\">\n" +
                "            <li>Notre intérêt légitime à garantir le meilleur niveau de fonctionnement et de qualité de notre site\n" +
                "              internet grâce notamment aux statistiques de visites de ces derniers</li>\n" +
                "            <li>Votre consentement lorsque celui-ci est requis. </li>\n" +
                "          </ul>\n" +
                "        </div>\n" +
                "        <ul>\n" +
                "    </article>\n" +
                "\n" +
                "\n" +
                "    <h2>ARTICLE 6. DONNEES PERSONNELLES TRAITEES</h2>\n" +
                "    <section>\n" +
                "      <p>\n" +
                "        Le caractère obligatoire ou facultatif des Données Personnelles demandées et les éventuelles conséquences d'un\n" +
                "        défaut de réponse à votre égard sont précisés lors de leur(s) collecte(s).\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        Vous pouvez consulter le détail des Données Personnelles que nous sommes susceptibles d’avoir sur vous dans le\n" +
                "        tableau présenté ci-dessous :\n" +
                "\n" +
                "      </p>\n" +
                "      <div>\n" +
                "        <table class=\"table-grey\" cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "            <td width=\"30%\">La gestion de votre inscription et le suivi de votre Compte</td>\n" +
                "            <td>\n" +
                "              <p><u>Données Personnelles de l’Utilisateur :</u></p>\n" +
                "              <ul class=\"list list-dashed list-min-margin\">\n" +
                "                <li>Nom ;\n" +
                "                <li>Prénom ;\n" +
                "                <li>Adresse e-mail ;\n" +
                "                <li>Mot de passe ;\n" +
                "                <li>Catégorie (Académique / Industriel / Autre acteur de l’Innovation) ;\n" +
                "                <li>Informations relatives au profil de l’Utilisateur si applicable (parcours, expériences, diplômes,\n" +
                "                  compétences) ;\n" +
                "                <li>Informations relatives à l’identification de la personne responsable de la page entreprise si\n" +
                "                  applicable ;</li>\n" +
                "              </ul>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>Répondre à vos demandes d’informations sur nos Services</td>\n" +
                "            <td>\n" +
                "              <ul class=\"list list-dashed list-min-margin\">\n" +
                "                <br />\n" +
                "                <li>Nom ;\n" +
                "                <li>Prénom ;\n" +
                "                <li>Adresse e-mail ;</li>\n" +
                "              </ul>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>Gérer et répondre à vos demandes d’exercice de droits « Informatique et Libertés »</td>\n" +
                "            <td>\n" +
                "              <ul class=\"list list-dashed list-min-margin\">\n" +
                "                <br />\n" +
                "                <li>Nom ;\n" +
                "                <li>Prénom ;\n" +
                "                <li>Adresse email et/ou postale en fonction de l’adresse renseignée au sein de la demande ;\n" +
                "                <li>Le cas échéant, justificatif d’identité.</li>\n" +
                "              </ul>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>Assurer le bon fonctionnement et l’amélioration permanente de notre Plateforme et de ses fonctionnalités\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <p>\n" +
                "                Le détail des cookies et traceurs utilisés est disponible au sein de notre Charte Cookies.\n" +
                "              </p>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "\n" +
                "        </table>\n" +
                "      </div>\n" +
                "    </section>\n");

        pdcpSb.append(" <h2>ARTICLE 7. DESTINATAIRE DE VOS DONNEES PERSONNELLES</h2>\n" +
                "    <section>\n" +
                "      <p>Dans la limite de leurs attributions respectives et pour les finalités rappelées à l’article 5, les principales\n" +
                "        personnes qui seront susceptibles d’avoir accès à vos Données Personnelles sont les suivantes :</p>\n" +
                "      <ul class=\"list list-red-square\">\n" +
                "        <li>Le personnel habilité des différents services de LINKINNOV (services administratifs, comptabilité,\n" +
                "          marketing, commercial, logistique et informatique, etc.) ;\n" +
                "        <li>Les prestataires chargés de la gestion de la Plateforme ;\n" +
                "        <li>Les Académiques afin d’exécuter les Prestations ;\n" +
                "        <li>Les Industriels afin d’exécuter les Prestations ;\n" +
                "        <li>Les services publics exclusivement pour répondre aux obligations légales ;\n" +
                "        <li>Le personnel habilité de nos sous-traitants (si sous-traitant il y a) ;\n" +
                "        <li>S’il y a lieu, les juridictions concernées, médiateurs, experts-comptables, commissaires aux comptes,\n" +
                "          avocats, huissiers, sociétés de recouvrement, autorités de police ou de gendarmerie en cas de vol ou de\n" +
                "          réquisition judiciaire, secours ;\n" +
                "        <li>Les tiers susceptibles de déposer des cookies sur vos terminaux (ordinateurs, tablettes, téléphones\n" +
                "          portables…) lorsque vous y consentez\n" +
                "        <li>Tout autre utilisateur de la Plateforme.</li>\n" +
                "      </ul>\n" +
                "      <p>\n" +
                "        Vos Données Personnelles ne sont communiquées, échangées, vendues ou louées à aucune autre personne que celles\n" +
                "        mentionnées ci-dessus.\n" +
                "      </p>\n" +
                "      <p class=\"grey-blc-title\">\n" +
                "        Plus de détail sur la liste de nos prestataires :\n" +
                "      </p>\n" +
                "      <div class=\"grey-blc\">\n" +
                "        <p>\n" +
                "          LINKINNOV a fait appel au prestataire ARKE’UP, dont l’adresse se situe 23 Bd Poissonière 75002 PARIS et\n" +
                "          immatriculée au RCS de Paris sous le numéro 538 662 602,\n" +
                "        </p>\n" +
                "      </div>\n" +
                "    </section>\n" +
                "\n" +
                "    <h2>ARTICLE 8. DUREE DE CONSERVATION DES DONNEES PERSONNELLES</h2>\n" +
                "    <section>\n" +
                "      <p>\n" +
                "        Nous conservons vos Données Personnelles uniquement le temps nécessaire pour les finalités poursuivies, telles\n" +
                "        que décrites à l’article 5, et synthétisées dans le tableau présenté ci-dessous :\n" +
                "      </p>\n" +
                "      <div>\n" +
                "        <table class=\"table-grey\" cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "            <td width=\"30%\">La gestion de votre inscription et le suivi de votre Compte </td>\n" +
                "            <td>\n" +
                "              <ul class=\"list list-dashed\">\n" +
                "                <br>\n" +
                "                <li><b>Cinq (5) ans</b> à compter de la suppression du Compte de l’Utilisateur</li>\n" +
                "              </ul>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>Assurer l’intermédiation entre les Académiques et les Industriels</td>\n" +
                "            <td>\n" +
                "              <ul class=\"list list-dashed\">\n" +
                "                <br>\n" +
                "                <li><b>Cinq (5) ans</b> à compter de la suppression du Compte de l’Utilisateur</li>\n" +
                "              </ul>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>Répondre à vos demandes sur nos Services </td>\n" +
                "            <td>\n" +
                "              <ul class=\"list list-dashed\">\n" +
                "                <br>\n" +
                "                <li><b>Cinq (5) ans</b> à compter de la suppression du Compte de l’Utilisateur</li>\n" +
                "              </ul>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>Vous proposer des offres commerciales pour nos Services et pour ceux de nos partenaires</td>\n" +
                "            <td>\n" +
                "              <ul class=\"list list-dashed\">\n" +
                "                <br>\n" +
                "                <li><b>Trois (3) ans </b> à compter de la collecte des Données Personnelles ou du dernier contact\n" +
                "                  émanant de l’Utilisateur </li>\n" +
                "              </ul>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>Gérer et répondre à vos demandes d’exercice de droits « Informatique et libertés »</td>\n" +
                "            <td>\n" +
                "              <ul class=\"list list-dashed\">\n" +
                "                <br>\n" +
                "                <li><b>Un (1) an </b> en cas d’exercice du droit d'accès ou de rectification </li>\n" +
                "                <li><b>Trois (3) ans </b> en cas d'exercice du droit d'opposition.</li>\n" +
                "              </ul>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>Assurer le bon fonctionnement et l’amélioration permanente de notre Plateforme et de ses fonctionnalités\n" +
                "            </td>\n" +
                "            <td>\n" +
                "              <ul class=\"list list-dashed\">\n" +
                "                <br>\n" +
                "                <li><b>Treize (13) mois</b>, puis les données sont supprimées ou anonymisées.</li>\n" +
                "              </ul>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </div>\n" +
                "    </section>\n" +
                "\n" +
                "    <h2>ARTICLE 9. VOS DROITS</h2>\n" +
                "    <section>\n" +
                "      <p>Conformément à la Loi IEL et au RGPD, vous disposez des droits suivants :</p>\n" +
                "      <ul class=\"list list-red-square\">\n" +
                "        <li>Droit d’accès, de rectification, de mise à jour, de complétude de vos Données Personnelles (<a\n" +
                "            href=\"https://www.cnil.fr/fr/comprendre-vos-droits\"><b>en savoir plus</b></a>) ;</li>\n" +
                "        <li>Droit de verrouillage ou d’effacement de vos Données Personnelles, lorsqu’elles sont inexactes, incomplètes,\n" +
                "          équivoques, périmées, ou dont la collecte, l'utilisation, la communication ou la conservation est interdite\n" +
                "          (<a\n" +
                "            href=\"https://www.legifrance.gouv.fr/affichTexteArticle.do;jsessionid=F99FCEDCD62590E5C611B9152B030E99.tplgfr21s_2?idArticle=LEGIARTI000033219719&cidTexte=LEGITEXT000006068624&dateTexte=20180207\"><b>en\n" +
                "              savoir plus</b></a>) ;</li>\n" +
                "        <li>Droit de retirer à tout moment votre consentement ;</li>\n" +
                "        <li>Droit à la limitation du Traitement de vos Données Personnelles ;</li>\n" +
                "        <li>Droit d’opposition au Traitement de vos Données Personnelles (<a\n" +
                "            href=\"https://www.cnil.fr/fr/le-droit-dopposition-refuser-lutilisation-de-vos-donnees\"><b>en savoir\n" +
                "              plus</b></a>) ;</li>\n" +
                "        <li>Droit à la portabilité des Données Personnelles que vous nous avez fournies, lorsque vos données font\n" +
                "          l’objet de Traitements automatisés fondés sur votre consentement ou sur un contrat ;</li>\n" +
                "        <li>Droit de définir le sort de vos Données Personnelles après votre mort et de choisir que nous communiquions\n" +
                "          (ou non) vos Données Personnelles à un tiers que vous aurez préalablement désigné.\n" +
                "          En cas de décès et à défaut d’instructions de votre part, nous nous engageons à détruire vos Données\n" +
                "          Personnelles, sauf si leur conservation s’avère nécessaire à des fins probatoires ou pour répondre à une\n" +
                "          obligation légale.</li>\n" +
                "\n" +
                "      </ul>\n" +
                "      <p>\n" +
                "        Vous pouvez exercer vos droits en adressant un courrier électronique à l’adresse : connect@linkinnov.com\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        Enfin, vous pouvez également introduire une réclamation auprès des autorités de contrôle et notamment de la\n" +
                "        CNIL, dont le site internet propose une <a href=\"https://www.cnil.fr/fr/plaintes\"><b>page dédiée au dépôt de\n" +
                "            plainte</b></a>.\n" +
                "      </p>\n" +
                "    </section>\n" +
                "\n" +
                "\n" +
                "    <h2>ARTICLE 10. DONNEES DE CONNEXION ET COOKIES </h2>\n" +
                "    <section>\n" +
                "      <p>\n" +
                "        Nous pouvons faire usage sur notre Plateforme de données de connexion (date, heure, adresse internet, protocole\n" +
                "        de votre terminal, pages consultées) et des cookies (petits fichiers enregistrés sur votre terminal) permettant\n" +
                "        de vous identifier, de mémoriser vos consultations notamment relatives aux pages consultées ainsi que de mesurer\n" +
                "        les utilisations de notre Plateforme.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        Vous pouvez consentir, refuser ou choisir le type de cookies dont vous acceptez le dépôt sur vos terminaux.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        Pour en savoir plus sur les cookies (comment les gérer, les supprimer, les identifier), vous pouvez consulter la\n" +
                "        <a href=\"https://www.cnil.fr/fr/cookies-les-outils-pour-les-maitriser#c5554\"><b>page dédiée du site internet de\n" +
                "            la CNIL.</b></a>\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        Enfin, pour en savoir plus sur les cookies et autres traceurs que nous pouvons déposer sur votre terminal dans\n" +
                "        le cadre de votre utilisation de la Plateforme, nous vous invitons à consulter notre <b>Charte Cookies</b>.\n" +
                "      </p>\n" +
                "    </section>\n" +
                "\n" +
                "\n" +
                "    <h2>ARTICLE 11. TRANSFERTS DE DONNEES PERSONNELLES HORS UNION EUROPEENNE </h2>\n" +
                "    <section>\n" +
                "      <p>\n" +
                "        Par principe, LINKINNOV héberge les données des Utilisateurs résidants dans l’Union Européenne au sein de Data Centers situés dans l’Union Européenne.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        Néanmoins, au regard de la nature de son activité, et sous réserve de vous en informer préalablement, LINKINNOV pourra être amenée à réaliser des transferts de vos données à destination de sous-traitants situés hors Union Européenne. Dans ce cas LINKINNOV vous indiquera les mesures prises afin de contrôler ce transfert et de s’assurer du respect de sa conformité.\n" +
                "      </p>\n" +
                "    </section>");

        pdcpSb.append("<h2>ARTICLE 12. SECURITE</h2>\n" +
                "    <section>\n" +
                "      <p>\n" +
                "        LINKINNOV et ses éventuels sous-traitants s’engagent à mettre en œuvre toutes les mesures techniques et\n" +
                "        organisationnelles afin d’assurer la sécurité des Traitements de Données Personnelles et leur confidentialité,\n" +
                "        en application de la Loi IEL et du RGPD.\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        A ce titre, LINKINNOV prend les précautions utiles, au regard de la nature de vos Données Personnelles et des\n" +
                "        risques présentés par les Traitements mis en œuvre via la Plateforme, pour préserver la sécurité des Données\n" +
                "        Personnelles et, notamment, empêcher qu’elles soient déformées, endommagées, ou que des tiers non autorisés y\n" +
                "        aient accès (procédé d’authentification des Utilisateurs avec accès personnel et sécurisé via des identifiants\n" +
                "        et mots de passe confidentiels, sécurité de l’accès aux postes informatiques, identifiants et mots de passe pour\n" +
                "        toutes nos applications métiers, gestion des habilitations pour l’accès aux données par VPN dans le cadre des\n" +
                "        connexions à distance…).\n" +
                "      </p>\n" +
                "    </section>\n" +
                "  </div>\n" +
                "</div>");

        DynamicPageDTO pdcpDynamicPageDTO = new DynamicPageDTO();
        pdcpDynamicPageDTO.setId("uuid-pdcp");
        pdcpDynamicPageDTO.setContent(pdcpSb.toString());
        save(pdcpDynamicPageDTO);

        idPages.forEach(id -> {
            DynamicPageDTO pageDTO = new DynamicPageDTO();
            pageDTO.setId(id);
            pageDTO.setContent(content);
            save(pageDTO);
        });
    }
}
