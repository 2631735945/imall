<%--
  Created by IntelliJ IDEA.
  User: 86156
  Date: 2022/8/22
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="header.jsp" %>
</head>
<body>
<form id="formId" class="layui-form layui-form-pane" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">请选择分类</label>
        <div class="layui-input-inline">
            <select name="quiz1">
                <option value="">请选择一级分类</option>
                <option value="100001">家用电器</option>
                <option value="100002">数码3C</option>
                <option value="100003">服装箱包</option>
                <option value="100004">食品生鲜</option>
                <option value="100005">酒水饮料</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select name="categoryId">
                <option value="">请选择二级分类</option>
                <option value="100006">冰箱11</option>
                <option value="100007">电视</option>
                <option value="100008">洗衣机</option>
                <option value="100009">空调</option>
                <option value="100010">电热水器</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">商品名</label>
        <div class="layui-input-block">
            <input type="text" name="name" lay-verify="title" autocomplete="off" placeholder="请输入商品名"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">商品子标题</label>
        <div class="layui-input-block">
            <input type="text" name="subtitle" lay-verify="title" autocomplete="off" placeholder="请输入商品子标题"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">价格</label>
        <div class="layui-input-block">
            <input type="text" name="price" lay-verify="title" autocomplete="off" placeholder="请输入价格" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">库存</label>
        <div class="layui-input-block">
            <input type="text" name="stock" lay-verify="title" autocomplete="off" placeholder="请输入库存"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <input type="radio" name="status" value="1" title="在售" checked="">
            <input type="radio" name="status" value="2" title="下架">
            <input type="radio" name="status" value="3" title="删除">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">图片上传</label>
        <div class="layui-input-block">
            <div class="layui-upload">
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="demo1" width="150px" height="150px">
                    <input type="hidden" name="mainImage" id="mainImage"/>
                    <p id="demoText"></p>
                </div>
                <%--<div style="width: 95px;">
                    <div class="layui-progress layui-progress-big" lay-showpercent="yes" lay-filter="demo">
                        <div class="layui-progress-bar" lay-percent=""></div>
                    </div>
                </div>--%>
                <button type="button" class="layui-btn" id="test1">上传图片</button>
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">商品详情</label>
        <div class="layui-input-block">
            <textarea name="detail" id="detailId" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" onclick="submitForm()" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交
            </button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="/static/kindeditor/kindeditor.js" type="text/javascript" charset="utf-8"></script>
<script>
    var form;
    layui.use(['form','upload'], function () {
        form = layui.form;
        var upload = layui.upload;

        //常规使用 - 普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: '/upload/uploadImage' //此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });

                // element.progress('demo', '0%'); //进度条复位
                layer.msg('上传中', {icon: 16, time: 2});
            }
            ,done: function(jsonResult){
                //如果上传失败
                if(jsonResult.code == 1){
                    return layer.msg('上传失败');
                }
                //上传成功的一些操作
                //……
                $('#mainImage').val(jsonResult.data);
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
            //进度条
            ,progress: function(n, elem, e){
                element.progress('demo', n + '%'); //可配合 layui 进度条元素使用
                if(n == 100){
                    layer.msg('上传完毕', {icon: 1});
                }
            }
        });
        // 发送ajax请求之后在select追加append之后
        // form.render('select');
    });

    KindEditor.ready(function(K) {
        var kingEditorParams = {
            afterBlur: function () { this.sync(); }
        }
        KindEditor.create('#detailId', kingEditorParams);
    });

    function submitForm() {
        $.post(
            '/product/add',
            $('#formId').serialize(),
            function (jsonResult) {
                console.log(jsonResult);
                if (jsonResult.code == 0) {
                    // mylayer.okMsg(jsonResult.msg);
                    // 获得当前弹出框的index
                    var index = parent.layer.getFrameIndex(window.name);
                    layer.msg(
                        jsonResult.msg,
                        {icon: 1, time: 1000},
                        function () { // msg弹出1秒后消失触发这个函数
                            // 关闭弹出层
                            parent.layer.close(index);
                            // 刷新父页面
                            window.parent.location.reload();
                        }
                    );
                } else {
                    mylayer.errorMsg(jsonResult.msg);
                }
            },
            'json'
        );
    }
</script>
</body>
</html>
