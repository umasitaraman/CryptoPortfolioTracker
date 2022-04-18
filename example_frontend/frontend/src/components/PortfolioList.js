import React from "react";
import PortfolioListAsset from "./PortfolioListAsset";

const PortfolioList = ({assets, assetQuantityMap}) => {

    return (
        <ul className="list-group mt-2">
            <li className="assetlist-item list-group-item d-flex justify-content-between align-items-center">
                <span className="portfolio-asset-item"></span>
                <span className="portfolio-asset-item">Asset</span>
                <span className="portfolio-asset-item">Quantity</span>
                <span className="portfolio-asset-item">Value</span>
            </li>
            {assets.map((asset) => {
                return <PortfolioListAsset key={asset.id} asset={asset} assetQuantityMap={assetQuantityMap}/>;
            })}
        </ul>
    );
};

export default PortfolioList;