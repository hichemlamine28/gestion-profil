FROM rabbitmq:3.7.8-management
RUN rabbitmq-plugins enable --offline rabbitmq_amqp1_0

ADD ./init_script/rabbitmq/rabbitmq.conf /etc/rabbitmq/
RUN chmod 777 /etc/rabbitmq/rabbitmq.conf

ADD ./init_script/rabbitmq/rabbit.app /usr/lib/rabbitmq/lib/rabbitmq_server-3.7.8/ebin/
RUN chmod 777 /usr/lib/rabbitmq/lib/rabbitmq_server-3.7.8/ebin/rabbit.app