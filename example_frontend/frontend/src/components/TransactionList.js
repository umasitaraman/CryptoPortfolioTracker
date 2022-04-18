import React from "react";
import Transaction from "./Transaction";

const TransactionList = ({transactions, assetMap}) => {

    return (
        <ul className="list-group mt-2">
            <li className="assetlist-item list-group-item d-flex justify-content-between align-items-center">
                <span className="transaction-item">Date</span>
                <span className="transaction-item">Asset</span>
                <span className="transaction-item">Quantity</span>
                <span className="transaction-item">Value</span>
                <span className="transaction-item">Type</span>
            </li>
            {transactions.map((transaction) => {
                return <Transaction key={transaction.transactionDate} transaction={transaction} assetName={assetMap[transaction.assetId].name} />;
            })}
        </ul>
    );
};

export default TransactionList;