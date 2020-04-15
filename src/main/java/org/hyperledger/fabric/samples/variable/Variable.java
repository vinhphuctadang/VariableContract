/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.hyperledger.fabric.samples.variable;

import java.util.ArrayList;
import java.util.List;

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

import com.owlike.genson.Genson;

/**
 * Java implementation Variable set-get
 */
@Default
public final class Variable implements ContractInterface {

    private final Genson genson = new Genson();

    @Transaction()
    public void initLedger(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();
        stub.putStringState("x", "");
    }

    @Transaction()
    public void set(final Context ctx, final String value) {
        ChaincodeStub stub = ctx.getStub();
        stub.putStringState("x", value);
    }

    @Transaction()
    public String get(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();
        String x = stub.getStringState("x");
        return x;
    }

    @Transaction()
    public byte[] who(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();
        return stub.getCreator();
    }
}
