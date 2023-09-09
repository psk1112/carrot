        document.addEventListener("DOMContentLoaded", function() {
            var modifyButtons = document.querySelectorAll(".modify-button");
            var deleteButtons = document.querySelectorAll(".delete-button");
            var writeButtons = document.getElementById("writeBtn");

            // 삭제 버튼 클릭 시
            deleteButtons.forEach(function(button) {
                button.addEventListener("click", function(event) {
                    var row = event.target.closest("tr");
                    var faqSeq = row.getAttribute("faq-seq-id");

                    var data = {
                        faqSeq : faqSeq
                    }

                    var jsonData = JSON.stringify(data);

                    Swal.fire({
                        title: '정말로 삭제하시겠습니까?',
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#d33',
                        cancelButtonColor: '#3085d6',
                        confirmButtonText: '삭제',
                        cancelButtonText: '취소'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            // AJAX 요청으로 서버에 데이터 전송
                            fetch("/admin/faq", {
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
                                        text: '게시물 삭제를 완료했습니다.',
                                        icon: 'success',
                                        confirmButtonText: '확인'
                                    }).then(() => {
                                         window.location.reload();
                                    });
                                } else {
                                    Swal.fire('게시물 삭제를 실패했습니다.');
                                }
                            })
                            //AJAX END
                        }
                    });
                });
            });


//            // 수정 버튼 클릭 시
//            modifyButtons.forEach(function(button) {
//                button.addEventListener("click", function(event) {
//                    var row = event.target.closest("tr");
//                    var faqSeq = row.getAttribute("faq-seq-id");
//                    var data = {
//                        faqSeq : faqSeq
//                    }
//                    var jsonData = JSON.stringify(data);
//
//                    //ajax
//                    fetch("/admin/modify-faq", {
//                        method: "POST",
//                        headers: {
//                            "Content-Type": "application/json;charset=UTF-8"
//                        },
//                        body: jsonData
//                    })
//                    .then(response => response.text())
//                    .then(html => {
//                        document.body.innerHTML = html;
//                        var scriptElement = document.createElement('script');
//                            scriptElement.src = '/admin/js/faqModifyPage.js';
//
//                        // 스크립트가 로드되면 실행할 콜백 함수
//                        scriptElement.onload = function() {
//                            console.log('스크립트 로드 완료');
//                            // 이곳에서 새로운 스크립트의 함수를 호출하거나 다른 작업 수행 가능
//                        };
//
//                        // 스크립트 요소를 body에 추가
//                        document.body.appendChild(scriptElement);
//                    });
//                });
//            });

        });
