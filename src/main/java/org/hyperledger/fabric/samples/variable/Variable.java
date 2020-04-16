/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.hyperledger.fabric.samples.variable;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;
import java.util.Random;
import com.owlike.genson.Genson;

/**
 * Java implementation Variable set-get
 */
 @Contract(
         name = "Variable",
         info = @Info(
                 title = "Variable contract",
                 description = "First example for learning",
                 version = "0.0.1-SNAPSHOT",
                 license = @License(
                         name = "Apache 2.0 License",
                         url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                 contact = @Contact(
                         email = "f.carr@example.com",
                         name = "F Carr",
                         url = "https://hyperledger.example.com")))
@Default
public final class Variable implements ContractInterface {

    private final Genson genson = new Genson();
    private final Random random = new Random();
    @Transaction()
    public void initLedger(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();
        stub.putStringState("x", "");
    }

    @Transaction()
    public void set(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();
        int x = random.nextInt(3)+1;
        stub.putStringState("x", x+"");
    }

    @Transaction()
    public String get(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();
        String x = stub.getStringState("x");
        return x;
    }

    @Transaction()
    public String who(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();
        byte[] creator = stub.getCreator();
        return new String(Base64.encodeBase64(creator));
    }
}
