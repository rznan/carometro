function copyToClipboard(text) {
    navigator.clipboard.writeText(text).then(() => {
        alert("Link copiado!");
    });
}

$(document).ready(function () {
    $('#alunoNome').select2({
        placeholder: 'Digite o nome do aluno',
        ajax: {
            url: '/alunos/buscar',
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    nome: params.term
                };
            },
            processResults: function (data) {
                return {
                    results: data.map(aluno => ({
                        id: aluno.id,
                        text: aluno.nome
                    }))
                };
            },
            cache: true
        }
    });

    $('#alunoNome').on('select2:select', function (e) {
        $('#alunoId').val(e.params.data.id);
    });
});
