window.onload = function () {
    //当页面加载完成，我们需要绑定各种事件

    var fruitTbl = document.getElementById("tal_fruit");
    //获取表格中的所有行
    var rows = fruitTbl.rows;
    for (var i = 1; i < rows.length - 1; i++) {
        var ti = rows[i];
        //1.绑定鼠标悬浮以及离开时设置背景颜色事件
        //方法不加小括号，表示方法绑定到这个事件
        ti.onmouseover = showBgColor;
        ti.onmouseout = clearBgColor;

        //获取tr这一行的所有单元格
        var cells = ti.cells;
        var priceTd = cells[1];

        //2.绑定鼠标悬浮在单价单元格变手势的事件
        priceTd.onmouseover = showHand;
        //3.绑定鼠标点击单价单元格的事件
        priceTd.onclick = editPrice;

        //7.绑定删除小图标的点击事件
        var img = cells[4].firstChild;
        if (img && img.tagName == "IMG") {
            //绑定单击事件
            img.onclick = deleteFruit;
        }

    }
}

//当鼠标悬浮时，显示背景颜色
function showBgColor() {
    // event : 当前发生的事件
    // event.srcElement : 事件源

    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;

        // td.parentElement 表示获取td的父元素 -> TR
        var tr = td.parentElement;

        //如果想要通过js代码设置某节点的样式，则需要加上 .style
        tr.style.backgroundColor = "royalblue";

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

//当鼠标悬浮在单价单元格时，显示手势
function showHand() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;
        // cursor : 光标
        td.style.cursor = "hand"
    }
}

//当鼠标点击单价单元格时进行价格编辑
function editPrice() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {

        var priceTd = event.srcElement;
        //目的是判断当前priceTD有子节点，而且第一个子节点是文本节点，TextNode对应的是3  ElementNote对应的是1
        if (priceTd.firstChild && priceTd.firstChild.nodeType == 3) {

            //innerText 表示设置或者获取当前节点的内部文本
            var oldPrice = priceTd.innerText;
            //innerHtml 表示设置当前节点的内部HTML
            priceTd.innerHTML = "<input type='text' size='4'/>";
            // <td><input type='text' size='4'/></td>
            var input = priceTd.firstChild;
            if (input.tagName == "INPUT") {
                input.value = oldPrice;
                //选中输入框内部的文本
                input.select();
                //4.绑定输入框失去焦点，失去焦点，更新单价
                input.onblur = updatePrice;

                //8.在输入框上绑定键盘按下的事件，此处我需要保证用户输入的是数字
                input.onkeydown = checkInput;
            }
        }
    }
}

//更新单价
function updatePrice() {
    if (event && event.srcElement && event.srcElement.tagName == "INPUT") {
        var input = event.srcElement;
        var newPrice = input.value;
        //input节点的父节点是td
        var priceTd = input.parentElement;
        priceTd.innerHTML = newPrice;

        //5.更新当前行的小计这一个格子的值
        //priceTd.parentElement td的父元素是tr
        updateXj(priceTd.parentElement);
    }
}

//更新指定行的小计
function updateXj(tr) {
    if (tr && tr.tagName == "TR") {
        var tds = tr.cells;
        var price = tds[1].innerText;
        var count = tds[2].innerText;
        //innerText获取到的值的类型是字符串类型，因此需要类型转换，才能进行数学运算
        var xj = parseInt(price) * parseInt(count);
        tds[3].innerText = xj;

        //6.更新总计
        updateZj();
    }
}

//更新总计
function updateZj() {
    var fruitTbl = document.getElementById("tal_fruit");
    var rows = fruitTbl.rows;
    var sum = 0;
    for (var i = 1; i < rows.length - 1; i++) {
        var tr = rows[i];
        var xj = parseInt(tr.cells[3].innerText);
        sum = sum + xj;
    }
    rows[rows.length - 1].cells[1].innerText = sum;
}

//删除指定行
function deleteFruit() {
    if (event && event.srcElement && event.srcElement.tagName == "IMG") {

        //alert表示弹出一个对话框，只有确定按钮
        //confirm表示弹出一个对话框，有确定和取消按钮。当点击确定返回true，否则返回false
        if (window.confirm("是否确认删除当前库存记录")) {
            var img = event.srcElement;
            var tr = img.parentElement.parentElement;
            var fruitTbl = document.getElementById("tal_fruit");
            fruitTbl.deleteRow(tr.rowIndex);

            updateZj();
        }
    }
}

//检验键盘摁下的值的方法
function checkInput() {
    var kc = event.keyCode;
    // 0~9 : 48~57
    //backspace : 8
    //enter : 13
    console.log(kc);

    if (!((kc >= 48 && kc <= 57) || kc == 8 || kc == 13)) {
        event.returnValue = false;
    }

    if (kc == 13) {
        event.srcElement.blur();
    }
}