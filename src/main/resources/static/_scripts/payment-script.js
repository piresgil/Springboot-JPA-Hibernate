/*
  QUANDO TIVER SEU BACKEND REAL:
    ------------------------------
    Para usar o backend Java real, você trocaria a variável `simulatedJsonData`
    pela chamada `Workspace`. Veja como seria:
*/
    async function carregarDadosDePagamentos() {
        const rawJsonContainer = document.getElementById('raw-json-container');
        const formattedDataContainer = document.getElementById('payment-data-container');

        try {
            // AQUI VOCÊ FAZ A REQUISIÇÃO REAL PARA SEU BACKEND JAVA
            const response = await fetch('/payments'); // OU a URL completa 'https://seu-backend.onrender.com/api/utilizadores'

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
                htmlTable += '<thead><tr><th>ID Pagamento</th><th>Data</th><th>ID Pedido</th></thead>';
                htmlTable += '<tbody>';

                realJsonData.forEach(payment => {
                    // ADAPTE AQUI PARA OS NOMES DAS PROPRIEDADES QUE SEU BACKEND RETORNA
                    htmlTable += `<tr>
                                    <td>${payment.id}</td>
                                    <td>${payment.moment}</td>
                                    <td>${payment.orderId}</td>
                                  </tr>`;
                });

                htmlTable += '</tbody></table>';
                formattedDataContainer.innerHTML = htmlTable;
            } else {
                formattedDataContainer.innerHTML = '<p>Nenhum pagamento encontrada.</p>';
            }

        } catch (error) {
            console.error('Erro ao carregar dados:', error);
            rawJsonContainer.innerHTML = '<p>Erro ao carregar JSON bruto.</p>';
            formattedDataContainer.innerHTML = '<p>Erro ao carregar os dados formatados.</p>';
        }
    }

    window.onload = carregarDadosDePagamentos;
