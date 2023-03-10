let index = {
  init: function () {
    $("#btn-save").on("click",()=>{
      this.save();
    });

    $("#btn-delete").on("click", () => {
      this.deleteById();
    });

    $("#btn-update").on("click", () => {
      this.update();
    });

    $("#btn-reply-save").on("click", () => {
      this.replySave();
    });
  },
  save: function () {
    let data = {
      title: $("#title").val(),
      content: $("#content").val(),
    }

    $.ajax({
      type : "POST",
      url : "/api/blog",
      data : JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType:"json"
    }).done(function (){
      alert("글쓰기가 완료 되었습니다.");
      location.href = "/";
    }).fail(function (error){
      alert(JSON.stringify(error));
    });
  },
  deleteById: function () {
    const id = $("#id").text();
    $.ajax({
      type: "DELETE",
      url: "/api/blog/".concat(id),
      dataType: "json"
    }).done(function () {
      alert("삭제가 완료 되었습니다.");
      location.href = "/";
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
  update: function () {
    const id = $("#id").val();
    let data = {
      title: $("#title").val(),
      content: $("#content").val()
    };
    $.ajax({
      type : "PUT",
      url : "/api/blog/".concat(id),
      data : JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType:"json"
    }).done(function (){
      alert("글수정이 완료 되었습니다.");
      location.href = "/";
    }).fail(function (error){
      alert(JSON.stringify(error));
    });
  },
  replySave: function () {

    let data = {
      userId: $("#userId").val(),
      blogId: $("#blogId").val(),
      content: $("#reply-content").val(),
    }

    $.ajax({
      type : "POST",
      url : `/api/blog/${data.blogId}/reply`,
      data : JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType:"json"
    }).done(function (){
      alert("댓글쓰기가 완료 되었습니다.");
      location.href = `/blog/${data.blogId}`;
    }).fail(function (error){
      alert(JSON.stringify(error));
    });
  },
  replyDelete: function (blogId, replyId) {
    $.ajax({
      type: "DELETE",
      url: `/api/blog/${blogId}/reply/${replyId}`,
      dataType: "json"
    }).done(function (resp){
      alert("댓글 삭제가 완료 되었습니다.");
      location.href = `/blog/${blogId}`;
    }).fail(function (error){
      alert(JSON.stringify(error));
    });
  }
}

index.init();