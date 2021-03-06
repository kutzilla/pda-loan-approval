/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camunda.bpm.getstarted.loanapproval;

import com.github.javafaker.Faker;
import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;

@ProcessApplication("Loan Approval App")
public class LoanApprovalApplication extends ServletProcessApplication {

    @PostDeploy
    public void onDeploymentFinished(ProcessEngine processEngine) {

        HashMap<String, Object> variables;
        Faker faker;

        for (int i = 0; i < 50; i++) {
            faker = new Faker();

            variables = new HashMap<String, Object>();

            variables.put("customerId", faker.idNumber().valid());
            variables.put("name", faker.name().lastName());

            double amount = (double) new Random().nextInt(10000);

            variables.put("amount", amount);

            ProcessInstance instance = processEngine.getRuntimeService()
                    .startProcessInstanceByKey("approve-loan", variables);

            String taskId = processEngine.getTaskService().createTaskQuery()
                    .processInstanceId(instance.getId()).singleResult().getId();

            processEngine.getTaskService().complete(taskId);
        }
    }
}
