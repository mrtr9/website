var CommonAction = {};
$.extend({
    "getSychronization": function (url, params, callback) {
        $.ajax({
            type: "get",
            url: url,
            data: params,
            async: false,//设置为同步
            success: function (result) {
                callback(result);
            }
        });
    },
    "postByJSON": function (url, params, callback) {
        $.ajax({
            type: "post",
            url: url,
            data: params,
            contentType: 'application/json;charset=UTF-8',
            success: function (result) {
                callback(result);
            }
        });
    },
    "put": function (url, params, callback) {
        $.ajax({
            type: "put", //提交方式
            url: url,//路径
            data: params,//数据，这里使用的是Json格式进行传输
            success: function (result) {//返回数据根据结果进行相应的处理
                callback(result);
            }
        });
    },
    "delete": function (url, params, callback) {
        $.ajax({
            type: "delete", //提交方式
            url: url,//路径
            data: params,//数据，这里使用的是Json格式进行传输
            success: function (result) {//返回数据根据结果进行相应的处理
                callback(result);
            }
        });
    },
    "msg": function (text) {
        layui.use('layer', function () { //独立版的layer无需执行这一句
            var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
            layer.msg(text);
        });
    },
    "alert": function (text) {
        layui.ues('layer', function () {
            var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
            layer.alert(text);
        });
    },
    "confirm": function (title, text, onSuccess) {
        layui.use('layer', function () { //独立版的layer无需执行这一句
            var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
            layer.confirm(text, {
                btn: ["确认", "关闭"],
                title: title,
                skin: 'layui-layer-lan',
                yes: function (index, layero) {
                    onSuccess();
                    layer.close(index);
                },
                closeBtn: 0
            });
        });
    },
    "iframe": function (url, option, params) {
        var _params = "";
        if (null != params) {
            for (var key in params) {
                _params += key + "=" + params[key] + "&";
            }
        }
        _params = _params.substring(0, _params.lastIndexOf("&"));
        layui.use('layer', function () { //独立版的layer无需执行这一句
            var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
            layer.open({
                title: option.title,
                type: 2,
                btn: option.btns,
                maxmin: option.maxmin,
                area: [option.width + "px", option.height + "px"], //宽高
                content: url + "?" + _params,
                yes: function (index, layero) {
                    //按钮【按钮一】的回调
                    if (typeof params.btn1 === 'function') {
                        params.btn1();
                    }
                    layer.close(index);
                }
                , btn2: function (index, layero) {
                    if (typeof params.btn2 === 'function') {
                        params.btn3();
                    }
                    console.log("btn2");
                    //按钮【按钮二】的回调

                    //return false 开启该代码可禁止点击该按钮关闭
                }
                , btn3: function (index, layero) {
                    if (typeof params.btn3 === 'function') {
                        params.btn3();
                    }
                    //按钮【按钮三】的回调

                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
        });
    },
    "getAllUrlParams": function () {
        //返回当前 URL 的查询部分（问号 ? 之后的部分）。
        var urlParameters = location.search;
        //声明并初始化接收请求参数的对象
        var requestParameters = new Object();
        //如果该求青中有请求的参数，则获取请求的参数，否则打印提示此请求没有请求的参数
        if (urlParameters.indexOf('?') != -1) {
            //获取请求参数的字符串
            var parameters = decodeURI(urlParameters.substr(1));
            //将请求的参数以&分割中字符串数组
            parameterArray = parameters.split('&');
            //循环遍历，将请求的参数封装到请求参数的对象之中
            for (var i = 0; i < parameterArray.length; i++) {
                requestParameters[parameterArray[i].split('=')[0]] = (parameterArray[i].split('=')[1]);
            }
            console.log("There is request parameters --->" + requestParameters);
        }
        else {
            console.info('There is no request parameters');
        }
        return requestParameters;
    },
    "upload": function (url, params, callback) {
        var formData = new FormData();
        for(var key in params){
            formData.append(key,params[key]);
        }
        $.ajax({
            type: "post",
            url: url,
            contentType: false,
            processData: false,
            data: formData,
            success: function (result) {
                callback(result);
            }
        });
    }
});
$.fn.extend({
    "setValue": function (data) {
        if (data != null && typeof(data) != 'undefined') {
            var spans = $(this).find("span");
            var ems = $(this).find("em");
            var inputs = $(this).find("input");
            var textAreas = $(this).find("textarea");
            var selects = $(this).find("select");
            if (spans.length > 0) {
                spans.each(function (i, o) {
                    var name = $(o).attr("name");
                    $(o).html(data[name]);
                });
            }
            if (ems.length > 0) {
                ems.each(function (i, o) {
                    var name = $(o).attr("name");
                    $(o).html(data[name]);
                });
            }
            if (inputs.length > 0) {
                inputs.each(function (i, o) {
                    var name = $(o).attr("name");
                    var type = $(o).attr("type");
                    if (typeof type === 'undefined') {
                        console.log(o);
                        console.log("请给上述标签指定type属性");
                    }
                    if (type === "text" || type === 'hidden') {
                        $(o).val(data[name]);
                    }
                    if (type === "checkbox") {
                        if ($(o).val() == data[name]) {
                            $(o).attr("checked", "checked");
                        }
                    }
                    if (type === "radio") {
                        if ($(o).val() == data[name]) {
                            $(o).attr("checked", "checked");
                        }
                    }
                });
            }
            if (textAreas.length > 0) {
                textAreas.each(function (i, o) {
                    var name = $(o).attr("name");
                    $(o).html(data[name]);
                });
            }
            if (selects.length > 0) {
                selects.each(function (i, o) {
                    var name = $(o).attr("name");
                    $(o).val(data[name]);
                });
            }
        } else {
            console.log("data is null or undefined");
        }
    },
    "setDisabled": function () {
        var inputs = $(this).find("input");
        var textAreas = $(this).find("textarea");
        var selects = $(this).find("select");
        if (inputs.length > 0) {
            inputs.each(function (i, o) {
                $(o).attr("disabled", "true");
            });
        }
        if (textAreas.length > 0) {
            textAreas.each(function (i, o) {
                $(o).attr("disabled", "true");
            });
        }
        if (selects.length > 0) {
            selects.each(function (i, o) {
                $(o).attr("disabled", "true");
            });
        }
    }
    ,
    "serializeFormToJSON": function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return JSON.stringify(o);
    },
    "setBtnDisabled": function () {
        this.addClass("layui-btn-disabled");
        this.attr("disabled", "true");
    },
    "setBtnEnabled": function () {
        this.removeClass("layui-btn-disabled");
        this.removeAttr("disabled");
    }
});