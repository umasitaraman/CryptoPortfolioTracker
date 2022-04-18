import React from "react";

const PortfolioListAsset = ({asset, assetQuantityMap}) => {
    return (
        <li className="assetlist-item list-group-item d-flex justify-content-between align-items-center">
            <span className="portfolio-asset-item"><img className="assetlist-image" src={asset.image} alt=""/></span>
            <span className="portfolio-asset-item">{asset.name}</span>
            <span className="portfolio-asset-item">{assetQuantityMap[asset.id]}</span>
            <span className="portfolio-asset-item">${(Math.round(assetQuantityMap[asset.id] * asset.current_price * 100) / 100).toFixed(2)}</span>
        </li>
    );
};

export default PortfolioListAsset;