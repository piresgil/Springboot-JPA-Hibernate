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
                let htmlTable = '<table>';
                htmlTable += '<thead><tr><th>ID</th><th>Data</th><th>Estado de Pedido</th><th>ID Cliente</th></thead>';
                htmlTable += '<tbody>';

                realJsonData.forEach(order => {
                    // ADAPTE AQUI PARA OS NOMES DAS PROPRIEDADES QUE SEU BACKEND RETORNA
                    htmlTable += `<tr>
                                    <td>${order.id}</td>
                                    <td>${order.moment}</td>
                                    <td>${order.order_status}</td>
                                    <td>${order.client_id}</td>
                                  </tr>`;
                });

                htmlTable += '</tbody></table>';
                formattedDataContainer.innerHTML = htmlTable;
            } else {
                formattedDataContainer.innerHTML = '<p>Nenhum Pedido encontrado.</p>';
            }

        } catch (error) {
            console.error('Erro ao carregar dados:', error);
            rawJsonContainer.innerHTML = '<p>Erro ao carregar JSON bruto.</p>';
            formattedDataContainer.innerHTML = '<p>Erro ao carregar os dados formatados.</p>';
        }
    }

    window.onload = carregarDadosDePedidos;
