import React from "react";

const PortfolioListAsset = ({asset, assetQuantityMap}) => {
    return (
        // <li className="coinlist-item list-group-item list-group-item-action d-flex
        //     justify-content-between align-items-center text-dark">
        <li className="coinlist-item list-group-item d-flex justify-content-between align-items-center">
            <img className="assetlist-image" src={asset.image} alt=""/>
            <span className="text-decoration-none">{asset.name}</span>
            <span className="text-decoration-none">{assetQuantityMap[asset.id]}</span>
        </li>
    );
};

export default PortfolioListAsset;