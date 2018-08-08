const Key = {
  ENTER:  13,
  UP:     38,
  DOWN:   40
};

function $(selector) {
    return document.querySelector(selector);
}

$("#search_text").addEventListener('input', searchHandler);
$('#search_text').addEventListener('keyup', keyboardHandler);
$('.autocomplete-list').addEventListener('mouseover', highlightText);
$('.search-form').addEventListener('submit', (e)=>{e.preventDefault();})

function searchHandler(evt) {
    const keyword = $("#search_text").value;

    if(keyword){
        fetch('/banchans/search?keyword='+keyword)
            .then(validateResponse)
            .then(({data}) => {
                renderAutocompleteBox(data);
            })
            .catch(handleError);
    }
}

function keyboardHandler(e){
    e.preventDefault();
    console.log("move happened!");
    if(e.keyCode==Key.UP){
        moveUp();
        return;
    }

    if(e.keyCode==Key.DOWN){
        moveDown();
        return;
    }

    if(e.keyCode==Key.ENTER){
        typeEnter(e);
        return;
    }
}

function moveUp(){
    const prevHighlight = $('.highlighting');
    let highlightNode;
    if(prevHighlight!=null){
            prevHighlight.classList.remove('highlighting');
            highlightNode = prevHighlight.previousElementSibling;
    }else{
            highlightNode = $('.autocomplete-list').lastChild;
    }

    highlightNode.classList.add('highlighting');

}

function moveDown(){
    const prevHighlight = $('.highlighting');
    let highlightNode;
    if(prevHighlight!=null){
            prevHighlight.classList.remove('highlighting');
            highlightNode = prevHighlight.nextElementSibling;
    }else{
            highlightNode = $('.autocomplete-list').firstChild;
    }

    highlightNode.classList.add('highlighting');

}

function typeEnter(evt){
    evt.preventDefault();
    const prevHighlight = $('.highlighting');

    if(prevHighlight!=null){
        $('.autocomplete-list').innerHTML = '';
        $("#search_text").value = prevHighlight.innerText;
    }

}

function highlightText(e) {
    const prevHighlight = $('.highlighting');

    if(prevHighlight!=null){
        prevHighlight.classList.remove('highlighting');
    }

    e.target.classList.add('highlighting');
}

function renderAutocompleteBox(data){
    $('.autocomplete-list').innerHTML = '';
    data.forEach((banchan)=>{
        $('.autocomplete-list').innerHTML += renderbanchanTemplate(banchan);
    });
}

function renderbanchanTemplate(banchan){
    let searchResult = highlightWord(banchan.title);
    let html = `<li class='autotext' id='banchan-${banchan.id}'>`+searchResult+`</li>`;
    return html;
}

function highlightWord(word){
    const keyword = $("#search_text").value;
    const changeWord = '<strong class="text-red">'+keyword+'</strong>';
    return word.replace(keyword,changeWord);
}

function validateResponse(response) {
    if (!response.ok) {
        throw new Error("페이지 로딩중에 에러가 발생했습니다. 새로고침 해주세요.");
    }
    return response.json();
}
