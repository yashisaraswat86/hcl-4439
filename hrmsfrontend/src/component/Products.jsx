import React, { useEffect, useState } from 'react';

function Products() {
    const [serverData, setServerData] = useState([]);

    useEffect(() => {
        async function getData() {
            try {
                const serverData = await fetch("http://localhost:8080");
                const jsondata = await serverData.text();
                console.log(jsondata);
                setServerData(jsondata);
            } catch (error) {
                console.log(error);
            }
        }

        getData();
    }, []);

    return (
        <div>
            <h1>Products</h1>
            <h2>{JSON.stringify(serverData)}</h2>
        </div>
    );
}

export default Products;
