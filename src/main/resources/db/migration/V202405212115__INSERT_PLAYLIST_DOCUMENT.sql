INSERT INTO document_template(id, description, content)
VALUES (1, 'Template to list the playlist of a quote request.',
        '<!DOCTYPE html>
<html>
<head>
    <title>Listagem Geral</title>
    <style>
        body{
            font-size: 12pt;
            font-family: Calibri;
            text-align: justify;
            width: 210mm;
            height: 297mm;
            line-height: 1;
        }
        .paginacao{
            padding-top: 2cm;
            padding-bottom: 2cm;
            padding-left: 3cm;
            padding-right: 1.5cm;
            width: 100%;
            height: 100%;
        }
        .text-center{
            text-align: center;
        }

        table{
            width: 100%;
            border-collapse: collapse;
        }
        .titulo{
            font-weight: bold;
            font-size: 20px;
        }
        .padding{
            padding-top: 5px;
            padding-bottom: 5px;
            padding-right: 5px;
            padding-left: 5px;
        }
        .col-10{
            width: 100%;
        }
        .avatar{
            border-radius: 50%;
        }
    </style>
</head>
<body>
    <h3 class="text-center">Repertório para o evento <span th:text="${eventName}"></span></h3>
    <h4 class="text-center">Banda <span th:text="${bandName}"></span></h4>

    <table border="1" class="text-center">
        <thead>
            <tr>
                <td class="padding titulo">#</td>
                <td class="padding titulo">Nome</td>
                <td class="padding titulo">Artista</td>
                <td class="padding titulo">Autor</td>
                <td class="padding titulo">Observação</td>
            </tr>
        </thead>
        <tbody th:each="music: ${musics}">
            <tr>
                <td class="padding">
                    <span th:text="${music.orderNumber}"></span>
                </td>
                <td class="padding">
                    <span th:text="${music.musicName}"></span>
                </td>
                <td class="padding">
                    <span th:text="${music.musicAuthor}"></span>
                </td>
                <td class="padding">
                    <span th:text="${music.musicArtist}"></span>
                </td>
                <td class="padding">
                    <span th:text="${music.observation}"></span>
                </td>
            </tr>
        </tbody>
    </table>
    <h5 class="text-center">Documento expedido em: <span th:text="${generatedAt}"></span></h5>
</body>
</html>
        ');
