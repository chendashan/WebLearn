//当鼠标悬浮时，显示背景颜色
function showBgColor() {
    // event : 当前发生的事件
    // event.srcElement : 事件源

    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;

        // td.parentElement 表示获取td的父元素 -> TR
        var tr = td.parentElement;

        //如果想要通过js代码设置某节点的样式，则需要加上 .style
        tr.style.backgroundColor = "navy";

        //
        var tds = tr.cells;
        for (var i = 0; i < tds.length; i++) {
            tds[i].style.color = "white";
        }

    }
}

//当鼠标离开时，恢复原始样式
function clearBgColor() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;

        var tr = td.parentElement;

        tr.style.backgroundColor = "transparent";

        var tds = tr.cells;
        for (var i = 0; i < tds.length; i++) {
            tds[i].style.color = "threeddarkshadow";
        }
    }
}