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

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Random;
import java.util.logging.Logger;

public class ProcessRequestDelegate implements JavaDelegate {

  private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

  public void execute(DelegateExecution execution) throws Exception {

    String customerId = (String) execution.getVariable("customerId");
    String name = (String) execution.getVariable("name");

    LOGGER.info("Processing request by " + customerId + " : " + name);

    double amount = (Double) execution.getVariable("amount");

    if (amount > 5000) {

      Random r = new Random();

      Thread.sleep(r.nextInt(2000) + 3000);
    }
    LOGGER.info("Request processed of " + customerId + " : " + name);
  }

}
