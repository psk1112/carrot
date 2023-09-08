document.addEventListener("DOMContentLoaded", function() {

    // tabId
    var tabSelector = [
        "#user-tab-pane",
        "#post-tab-pane",
        "#reply-tab-pane",
        "#faq-tab-pane",
        "#question-tab-pane"
    ]

    tabSelector.forEach(function(tabSelector){
        handleAddNewCategory(tabSelector);
        handleModifyCategory(tabSelector);
    });


    // 새 카테고리 추가
    function handleAddNewCategory(tabId){
        var addNewCategoryButton = document.querySelector(tabId + " .clickable-text");
        var newCateSaveButton = document.querySelector(tabId + " .newCate-save-button");
        var categoryTable = document.querySelector(tabId + " .tableBox table");
        var maxCategoryCount = 4;
        var currentCategoryCount = 0;

        addNewCategoryButton.addEventListener("click", function() {
            newCateSaveButton.removeAttribute("hidden");

            if (currentCategoryCount < maxCategoryCount) {
                // 새로운 <tr> 엘리먼트 생성
                var newRow = document.createElement("tr");

                // <td> 엘리먼트 생성
                var td = document.createElement("td");
                td.colSpan = "3";

                // 새로운 입력 필드 생성
                var newCategoryInput = document.createElement("input");
                newCategoryInput.type = "text";
                newCategoryInput.className = "newCate form-control";
                newCategoryInput.placeholder = "카테고리 이름을 입력하세요";

                // 입력 필드를 <td>에 추가
                td.appendChild(newCategoryInput);

                // <tr>에 <td>를 추가
                newRow.appendChild(td);

                // <tr>을 테이블에 추가
                categoryTable.appendChild(newRow);

                // 카테고리 개수 업데이트
                currentCategoryCount++;
            }else {
                Swal.fire("최대 4개까지 추가 가능합니다.");
            }
        });

        //카테고리 추가 저장
        newCateSaveButton.addEventListener("click", function() {
            var cateInputs = document.querySelectorAll(tabId + " .newCate");
            var categoryNames = [];
            var categoryKind = addNewCategoryButton.id;
            cateInputs.forEach(function(input){
                if(input.value.trim() !== ""){
                    categoryNames.push(input.value);
                }
            });

            //JSON
            var data = {
                categoryKind: categoryKind,
                categoryNames: categoryNames
            };

            var jsonData = JSON.stringify(data);

            //AJAX로 데이터 전달
            fetch("/admin/category", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                },
                body: jsonData
            })
            .then(response => response.json())
            .then(data => {
                if(data.statusCode === 200){
                    Swal.fire({
                        text: '카테고리 등록을 완료했습니다.',
                        icon: 'success',
                        confirmButtonText: '확인'
                    }).then(() => {
                         window.location.reload();
                    })
                }else if(data.statusCode === 401){
                    Swal.fire(data.body);
                }else {
                    Swal.fire('카테고리 등록을 실패했습니다.');
                }
            })

        })

    } //END handleAddNewCategory


    // 삭제 버튼 클릭
    var deleteButtons = document.querySelectorAll(".delete-button");

    deleteButtons.forEach(function(button) {
        button.addEventListener("click", function(event) {

            var categorySeq = event.target.getAttribute("data-id");
            var data = {
                categorySeq : categorySeq
            }

            var jsonData = JSON.stringify(data);

            Swal.fire({
                title: '정말로 삭제하시겠습니까?',
                text: "카테고리와 관련된 게시물 모두 삭제됩니다. ",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                confirmButtonText: '삭제',
                cancelButtonText: '취소'
            }).then((result) => {
                if (result.isConfirmed) {
                    // AJAX 요청으로 서버에 데이터 전송
                    fetch("/admin/category", {
                        method: "DELETE",
                        headers: {
                            "Content-Type": "application/json;charset=UTF-8"
                        },
                        body: jsonData
                    })
                    .then(response => response.json())
                    .then(data => {
                        if (data.statusCode === 200) {
                            Swal.fire({
                                text: '카테고리 삭제를 완료했습니다.',
                                icon: 'success',
                                confirmButtonText: '확인'
                            }).then(() => {
                                 window.location.reload();
                            })
                        } else {
                            Swal.fire('카테고리 삭제를 실패했습니다.');
                        }
                    })
                    //AJAX END
                }
            });
        });
    });


    // 수정 버튼 클릭
    function handleModifyCategory(tabId){
        var modifyButtons = document.querySelectorAll(tabId + " .modify-button");
        var saveButtons = document.querySelectorAll(tabId + " .modi-save-button");
        var categoryInputs = document.querySelectorAll(tabId + " .KindName");

        modifyButtons.forEach(function(button) {
            button.addEventListener("click", function(event) {

                // 현재 클릭한 수정 버튼의 입력필드 readonly해제
                var currentRow = event.target.closest("tr");
                var categoryInput = currentRow.querySelector(".KindName");

                categoryInput.removeAttribute("readonly");
                categoryInput.addEventListener("change", function () {
                    categoryInput.dataset.changed = "true";
                });

                //저장버튼 보이기
                saveButtons.forEach(function(saveButton) {
                    saveButton.removeAttribute("hidden");
                });
            });
        });

        //카테고리 목록에 있는 저장버튼 클릭
        saveButtons.forEach(function(saveButton) {
            saveButton.addEventListener("click", function(event) {

            // 값 배열에 담기
            var categoryKinds = [];
            var categorySequences = [];
            var categoryNames = [];

            categoryInputs.forEach(function(input) {
                var parts = input.id.split("_");

                categoryKinds.push(parts[0]);
                categorySequences.push(parts[1]);
                categoryNames.push(input.value);
            });

            // 데이터 객체 생성
            var data = {
                categoryKinds: categoryKinds,
                categorySequences: categorySequences,
                categoryNames: categoryNames
            };
            var jsonData = JSON.stringify(data);

            console.log(jsonData)
                // AJAX 요청으로 서버에 데이터 전송
                fetch("/admin/category", {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json;charset=UTF-8"
                    },
                    body: jsonData
                })
                .then(response => response.json())
                .then(data => {
                    if(data.statusCode === 200){
                        Swal.fire({
                            text: '카테고리 수정을 완료했습니다.',
                            icon: 'success',
                            confirmButtonText: '확인'
                        }).then(() => {
                             window.location.reload();
                        })
                    }else if(data.statusCode === 401){
                        Swal.fire(data.body)
                    }else {
                        Swal.fire('카테고리 수정을 실패했습니다.');
                    }
                })
                //AJAX END
            });
        });

    } //END handleModifyCategory

});