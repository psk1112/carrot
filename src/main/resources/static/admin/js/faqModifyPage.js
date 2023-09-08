document.addEventListener("DOMContentLoaded", function() {
    var cancelButtons = document.getElementById("cancel-button");
    var save = document.getElementById("save-button");
    var selectedSeq = parseInt(document.getElementById("selectedSeq").value);
    console.log(selectedSeq)

    // 취소버튼 클릭 시 페이지 이동
    cancelButtons.addEventListener("click", function(){
        window.location.href="/admin/faq";
    })


    save.addEventListener("click", function(){
       var faqKindSeq = document.getElementById("faqKindSeq").value;
       var faqTitle = document.getElementById("faqTitle").value;
       var faqContent = document.getElementById("faqContent").value;
    })


    // select 요소
    var selectElement = document.getElementById("faqKindSeq");

    // option 요소
    var optionElements = selectElement.querySelectorAll("option");

    // 각 option 요소를 반복
    optionElements.forEach(function(option) {
        var optionValue = option.getAttribute("value");

        if (optionValue == selectedFaqKindSeq) {
            option.selected = true;
        }
    });

});