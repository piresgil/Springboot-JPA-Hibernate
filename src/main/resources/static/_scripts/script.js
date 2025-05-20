// Seleciona o elemento de cabeçalho (header) da página e armazena na variável $header.
const $header = document.querySelector('header');

// Seleciona o primeiro elemento com a classe 'logo' e armazena na variável $logo.
// O uso de [0] é porque querySelectorAll retorna uma NodeList (uma espécie de array).
const $logo = document.querySelectorAll('.logo')[0];

// Seleciona o primeiro elemento com a classe 'nav-bar' e armazena na variável $navBar.
const $navBar = document.querySelectorAll('.nav-bar')[0];

// Seleciona o primeiro elemento com a classe 'menu' e armazena na variável $menu.
const $menu = document.querySelectorAll('.menu')[0];

// Seleciona todos os elementos que possuem o atributo 'wm-folder' e armazena na variável $listas.
// Esses elementos provavelmente são os cabeçalhos de listas que podem ser expandidas/colapsadas.
const $listas = document.querySelectorAll('[wm-folder]');

// Adiciona um listener de evento de 'scroll' na janela (window).
// Quando a página é rolada, a função toggleHeader será executada.
// O terceiro argumento 'false' indica que o listener será executado na fase de bubbling.
window.addEventListener('scroll', toggleHeader, false);

// Função que altera o estilo do cabeçalho, logo e navegação com base na posição de rolagem da página.
function toggleHeader() {
    // Verifica se a posição vertical da rolagem (pageYOffset) é maior que 60 pixels
    // E se o cabeçalho possui a classe 'max-header' (estilo de cabeçalho maior).
    if (window.pageYOffset > 60 && $header.classList.contains('max-header')) {
        // Remove a classe 'max-header' do cabeçalho.
        $header.classList.remove('max-header');
        // Adiciona a classe 'main-header' ao cabeçalho (estilo de cabeçalho menor).
        $header.classList.add('main-header');

        // Remove a classe 'max-logo' do cabeçalho (estilo de logo maior).
        $header.classList.remove('max-logo');
        // Adiciona a classe 'main-logo' ao cabeçalho (estilo de logo menor).
        $header.classList.add('main-logo');

        // Remove a classe 'max-nav' da barra de navegação (estilo de navegação maior).
        $navBar.classList.remove('max-nav');
        // Adiciona a classe 'main-nav' à barra de navegação (estilo de navegação menor).
        $navBar.classList.add('main-nav');
        // Remove a classe 'max-hamburger' do primeiro filho do menu (ícone de menu maior).
        $menu.firstElementChild.classList.remove('max-hamburger');
        // Adiciona a classe 'main-hamburger' ao primeiro filho do menu (ícone de menu menor).
        $menu.firstElementChild.classList.add('main-hamburger');
    // Caso a posição de rolagem seja menor ou igual a 60 pixels
    // E o cabeçalho possua a classe 'main-header' (estilo de cabeçalho menor).
    } else if (window.pageYOffset <= 60 && $header.classList.contains('main-header')) {
        // Adiciona a classe 'max-header' ao cabeçalho (estilo de cabeçalho maior).
        $header.classList.add('max-header');
        // Remove a classe 'main-header' do cabeçalho.
        $header.classList.remove('main-header');

        // Adiciona a classe 'max-logo' ao cabeçalho (estilo de logo maior).
        $header.classList.add('max-logo');
        // Remove a classe 'main-logo' do cabeçalho.
        $header.classList.remove('main-logo');

        // Adiciona a classe 'max-nav' à barra de navegação (estilo de navegação maior).
        $navBar.classList.add('max-nav');
        // Remove a classe 'main-nav' da barra de navegação.
        $navBar.classList.remove('main-nav');
        // Adiciona a classe 'max-hamburger' ao primeiro filho do menu (ícone de menu maior).
        $menu.firstElementChild.classList.add('max-hamburger');
        // Remove a classe 'main-hamburger' do primeiro filho do menu.
        $menu.firstElementChild.classList.remove('main-hamburger');
    }
}

// Adiciona um listener de evento de 'click' no elemento de menu ($menu).
// Quando o menu é clicado, a função toggleMenu será executada.
$menu.addEventListener('click', toggleMenu, false);

// Variável para controlar o estado do menu (se está aberto ou fechado).
var isOpen = false;

// Função que abre e fecha o menu de navegação.
function toggleMenu() {
    // Se o menu não estiver aberto.
    if (!isOpen) {
        // Adiciona a classe 'menu-opened' à barra de navegação, tornando-a visível.
        $navBar.classList.add('menu-opened');
        // Adiciona a classe 'close-btn' ao primeiro filho do menu, mudando seu visual para um botão de fechar.
        $menu.firstElementChild.classList.add('close-btn');
        // Define o estado do menu como aberto.
        isOpen = true;
    // Se o menu já estiver aberto.
    } else {
        // Remove a classe 'menu-opened' da barra de navegação, escondendo-a.
        $navBar.classList.remove('menu-opened');
        // Remove a classe 'close-btn' do primeiro filho do menu, revertendo ao visual de menu.
        $menu.firstElementChild.classList.remove('close-btn');
        // Define o estado do menu como fechado.
        isOpen = false;
    }
}

// Adiciona um listener de evento de 'click' na barra de navegação ($navBar).
// Quando um clique ocorre na barra de navegação, a função navClick será executada.
$navBar.addEventListener('click', navClick, false);

// Função que fecha o menu de navegação ao clicar em um link.
function navClick(evt) {
    // Verifica se o elemento alvo do clique (evt.target) é uma tag 'A' (um link).
    if (evt.target.tagName == 'A') {
        // Chama a função toggleMenu para fechar o menu.
        toggleMenu();
    }
}

// Itera sobre cada elemento na NodeList $listas (os cabeçalhos das listas com 'wm-folder').
$listas.forEach(folder => {
    // Adiciona um listener de evento de 'click' a cada 'folder'.
    folder.onclick = function (e) {
        // Seleciona o elemento irmão seguinte (nextElementSibling) do 'folder',
        // que presumivelmente é a lista (<ul>) a ser expandida/colapsada.
        const ul = folder.nextElementSibling;
        // Obtém o valor atual da propriedade 'display' do elemento da lista.
        const d = ul.style.display;
        // Define a propriedade 'display' da lista.
        // Se 'display' for 'none' (escondido), define como 'block' (visível).
        // Se 'display' for 'block' (visível), define como 'none' (escondido).
        ul.style.display = d === 'none' ? 'block' : 'none';
    };
});

/*

  QUANDO TIVER SEU BACKEND REAL:
    ------------------------------
    Para usar o backend Java real, você trocaria a variável `simulatedJsonData`
    pela chamada `Workspace`. Veja como seria:
*/
    async function carregarDadosDeUtilizadores() {
        const rawJsonContainer = document.getElementById('raw-json-container');
        const formattedDataContainer = document.getElementById('users-data-container');

        try {
            // AQUI VOCÊ FAZ A REQUISIÇÃO REAL PARA SEU BACKEND JAVA
            const response = await fetch('/users'); // OU a URL completa 'https://seu-backend.onrender.com/api/utilizadores'

            if (!response.ok) {
                throw new Error(`Erro HTTP ao carregar utilizadores! Status: ${response.status}`);
            }

            const realJsonData = await response.json(); // RECEBE O JSON REAL

            // --- PARTE 1: Exibir o JSON bruto real ---
            rawJsonContainer.innerHTML = '<pre>' + JSON.stringify(realJsonData, null, 2) + '</pre>';

            // --- PARTE 2: Formatar e exibir os dados em uma tabela ---
            formattedDataContainer.innerHTML = '';

            if (realJsonData && realJsonData.length > 0) {
                let htmlTable = '<table>';
                htmlTable += '<thead><tr><th>ID</th><th>Nome</th><th>Email</th><th>Idade</th></tr></thead>';
                htmlTable += '<tbody>';

                realJsonData.forEach(user => {
                    // ADAPTE AQUI PARA OS NOMES DAS PROPRIEDADES QUE SEU BACKEND RETORNA
                    htmlTable += `<tr>
                                    <td>${user.id}</td>
                                    <td>${user.nome}</td>
                                    <td>${user.email}</td>
                                    <td>${user.password}</td>
                                    <td>${user.phone}</td>
                                  </tr>`;
                });

                htmlTable += '</tbody></table>';
                formattedDataContainer.innerHTML = htmlTable;
            } else {
                formattedDataContainer.innerHTML = '<p>Nenhum utilizador encontrado.</p>';
            }

        } catch (error) {
            console.error('Erro ao carregar dados:', error);
            rawJsonContainer.innerHTML = '<p>Erro ao carregar JSON bruto.</p>';
            formattedDataContainer.innerHTML = '<p>Erro ao carregar os dados formatados.</p>';
        }
    }

    window.onload = carregarDadosDeUtilizadores;
