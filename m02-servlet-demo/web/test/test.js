window.onload = function () {
    //test1();
    //test2();
    //test3();
    test4();
}

function test1() {
    //对象形式
    var reg1 = new RegExp("abc");
    //直接量形式
    var reg2 = /abc/;
    var str = "abcdefg";
    var flag = reg2.test(str);
    console.log(flag);
}

function test2() {
    var reg1 = /o/;
    // g表示匹配的模式，表示全文匹配
    var reg2 = /o/g;
    // i表示忽略大小写
    var reg3 = /o/gi;
    var str = "hello world LOL";
    str = str.replace(reg3, '_');
    console.log(str);

    // $表示以什么结尾
    var reg4 = /hello$/;
    // m表示多行匹配
    var reg5 = /hello$/m;
    var str2 = "tom hello\nlee"
    var flag1 = reg4.test(str2);
    var flag2 = reg5.test(str2);
    console.log('flag1=' + flag1 + ', flag2=' + flag2);

}

function test3() {
    var reg = /\w/g;
    var str = "12a>3b_c|d\ne?g";
    str = str.replace(reg, "A");
    console.log(str);

    var reg = /\b/g;
    var str2 = "hello world 123 >abc abc*"
    str2 = str2.replace(reg, "A");
    console.log(str2);
}

function test4() {
    var reg1 = /[abc]/g;
    var reg2 = /[^abc]/g;
    var reg3 = /[a-e0-9]/g;
    var str = "acdefg>?129";
    console.log(str.replace(reg1, "A"));
    console.log(str.replace(reg2, "A"));
    console.log(str.replace(reg3, "A"));
}