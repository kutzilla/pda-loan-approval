FROM camunda/camunda-bpm-platform

RUN rm -rf /camunda/webapps/camunda-invoice

COPY target/loan-approval.war webapps/loan-approval.war
