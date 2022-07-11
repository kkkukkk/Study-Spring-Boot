/*
    reply module
*/

console.log("Reply Module.....");

//일회용 함수를 객체에 담아줌
//마지막 소괄호는 사용하겠다는 뜻
//아래부분에 ajax 들이 들어가게 됨
let replyService = (function(){

    function add(reply, callback, error){
        console.log("add reply..........");
        $.ajax({
            //전달받은 url
            url: "/reply/new",
            //매핑 type
            type: "post",
            //전달받은 데이터, js 객체는 key값에 쌍따옴표가 없으므로 JSON형식으로 바꿔주기 위해 stringify() 사용
            data : JSON.stringify(reply),
            //전달할 데이터 타입
            contentType : "application/json",
            //리턴된 데이터 타입
            // dataType: "json",
            //성공했을 때 실행할 함수, result에는 결과 값이 담겨저 있음
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            //실패했을 때 실행할 함수
            error: function(xhr, status, er){
                if(error){
                    console.log(xhr, status, er);
                }
            }
        });
    }

    function read(rno, callback, error) {
        console.log("read");
        $.ajax({
            url: "/reply/" + rno,
            type: "get",
            dataType: "json",
            success: function (reply) {
                if(callback){
                    callback(reply);
                }
            }
        });
    }

    function remove(rno, callback, error){
        console.log("delete");
        $.ajax({
            url: "/reply/" + rno,
            type: "delete",
            success: function (reply){
                if(callback){
                    callback(reply);
                }
            }
        })
    }

    function modify(reply, callback, error){
        console.log("modify");
        $.ajax({
            url: "/reply/" + reply.replyNumber + "/" + reply.replyWriter,
            type: "patch",
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8;",
            success: function(result){
                if(callback){
                    callback(result);
                }
            }
        });
    }

    //댓글 목록
    function getList(param, callback, error){
        //앞 값이 없으면 뒤 값이 들어감
        let page = param.page || 1;
        // 다른 속성 없이 JSON만 받아올 때,
        // JQuery 에서 제공하는 메소드, 아래 ajax와 동일하게 동작함
        $.getJSON("/reply/list/" + param.bno + "/" + page, function(replyPageDTO){
           if(callback){
               callback(replyPageDTO.total, replyPageDTO.list)
           }
           //실패했을 때의 메소드
        }).fail(function(xhr, status, er){
            if(error){
                error(er);
            }
        });
        // $.ajax({
        //     url: "/reply/list/" + param.bno + "/" + page,
        //     type: "get",
        //     dataType: "json",
        //     success: function(replyPageDTO){
        //         if(callback){
        //             callback(replyPageDTO.total, replyPageDTO.list);
        //         }
        //     }
        // });
    }

    //댓글 작성 시간(Javascript)
    function getReplyDateByJavascript(replyDate){
        let today = new Date();
        let rDate = new Date(replyDate);
        let gap = today.getTime() - rDate.getTime();

        if(gap < 1000 * 60 * 60 * 24){
            let h = rDate.getHours();
            let m = rDate.getMinutes();
            let s = rDate.getSeconds();

            return [(h < 10 ? '0' : '') + h,(m < 10 ? '0' : '') + m,(s < 10 ? '0' : '') + s].join(":");
        }else{
            let y = rDate.getFullYear();
            let m = rDate.getMonth() + 1;
            let d = rDate.getDate();

            return [y, (m < 10 ? '0' : '') + m, (d < 10 ? '0' : '') + d].join("-");
        }
    }

    //댓글 수정 시간(JAVA)
    function getReplyDateByController(replyDate){
        let result;
        $.ajax({
            url: "/time",
            type: "get",
            data: {replyDate : replyDate},
            //Ajax는 기본적으로 비동기식으로 동작하는데,
            //async를 false로 주어서 동기식으로 바꾸면
            //아래의 콜백 함수(success)의 연산이 모두 완료되어야 다음 작업이 진행된다.
            async: false,
            success: function(time){
                result = time;
            }
        });
        return result;
    }

    function test(reply, callback){
        $.ajax({
            url: "/reply/test",
            type: "get",
            success: function(result){
                if(callback){
                    callback(result);
                }
            }
        })
    }

    function test1(reply, callback){
        $.ajax({
            url: "/reply/test1/" + reply,
            type: "post",
            success: function(result){
                if(callback){
                    callback(result);
                }
            }
        })
    }

    function test2(callback){
        $.ajax({
            url: "/reply/test2",
            type: "get",
            dataType: "json",
            success: function(result){
                if(callback){
                    callback(result);
                }
            }
        })
    }

    function test3(reply, callback){
        $.ajax({
            url: "/reply/test3/" + reply.replyNumber + "/" + reply.replyWriter,
            type: "patch",
            data: JSON.stringify(reply),
            contentType: "application/json; charset='utf-8';",
            dataType: "json",
            success: function(reply){
                if(callback){
                    callback(reply);
                }
            }
        });
    }

    function test4(reply, callback){
        $.ajax({
            url: "/reply/test4/" + reply.page + "/" + reply.boardBno,
            type: "post",
            data: JSON.stringify(reply),
            contentType: "application/json; charset='utf-8';",
            dataType: "json",
            success: function(reply){
                if(callback){
                    callback(reply);
                }
            }
        })
    }


    // js객체 형태로 리턴
    return {add : add, read: read, remove: remove,
        modify: modify, getList : getList,
        test : test, test1 : test1, test2 : test2,
        test3 : test3, test4:test4, getReplyDateByJavascript:getReplyDateByJavascript,
        getReplyDateByController:getReplyDateByController};

})();