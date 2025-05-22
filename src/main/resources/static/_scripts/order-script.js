/*
  QUANDO TIVER SEU BACKEND REAL:
    ------------------------------
    Para usar o backend Java real, você trocaria a variável `simulatedJsonData`
    pela chamada `Workspace`. Veja como seria:
*/
    async function carregarDadosDePedidos() {
        const rawJsonContainer = document.getElementById('raw-json-container');
        const formattedDataContainer = document.getElementById('order-data-container');

        try {
            // AQUI VOCÊ FAZ A REQUISIÇÃO REAL PARA SEU BACKEND JAVA
            const response = await fetch('/orders'); // OU a URL completa 'https://seu-backend.onrender.com/api/utilizadores'

            if (!response.ok) {
                throw new Error(`Erro HTTP ao carregar categorias! Status: ${response.status}`);
            }

            const realJsonData = await response.json(); // RECEBE O JSON REAL

            // --- PARTE 1: Exibir o JSON bruto real ---
            rawJsonContainer.innerHTML = '<pre>' + JSON.stringify(realJsonData, null, 2) + '</pre>';

            // --- PARTE 2: Formatar e exibir os dados em uma tabela ---
            formattedDataContainer.innerHTML = '';


         if (realJsonData && realJsonData.length > 0) {
                     let htmlTable = '<table border="1" cellpadding="5">';
                     htmlTable += '<thead><tr><th>ID</th><th>Data</th><th>Status</th><th>Cliente</th></tr></thead><tbody>';

                     realJsonData.forEach(order => {
                        htmlTable += `<tr>
                                   <td>${order.id ?? 'N/A'}</td>
                                   <td>${order.moment ?? 'N/A'}</td>
                                   <td>${order.orderStatus ?? 'N/A'}</td>
                                   <td>${order.client?.id ?? 'N/A'}, ${order.client?.name ?? 'N/A'}</td>
                               </tr>`;
                     });

               htmlTable += '</tbody></table>';
               formattedDataContainer.innerHTML = htmlTable;
           }  else {
                formattedDataContainer.innerHTML = '<p>Nenhum Pedido encontrado.</p>';
            }

        } catch (error) {
            console.error('Erro ao carregar dados:', error);
            rawJsonContainer.innerHTML = '<p>Erro ao carregar JSON bruto.</p>';
            formattedDataContainer.innerHTML = '<p>Erro ao carregar os dados formatados.</p>';
        }
    }

    window.onload = carregarDadosDePedidos;
