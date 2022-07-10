## JavaScript正则表达式

正则表达式的用途：

- 模式验证：检测某个字符串是否符合规则，例如手机号等
- 匹配读取：将目标字符串中满足规则的部分读取出来，例如将文本中的邮箱读取出来
- 匹配替换：将目标字符串中满足标准的部分替换为其他字符串

正则表达式的使用三步骤：

1. 定义正则表达式对象
2. 定义待校验的字符串
3. 校验

定义正则表达式的两种方式：

```js
    //对象形式
var reg1 = new RegExp("abc");
//直接量形式
var reg2 = /abc/;
```

匹配模式：

```javascript
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
```

输出：

```
    hell_ w_rld L_L
    flag1=false, flag2=true
```

#### 元字符

在正则表达式中被赋予特殊含义的字符，不能被当做普通字符使用，如果要匹配原字符本身，需要在元字符前面加上"\"

```js
    var reg = /\w/g;
var str = "12a>3b_c|d\ne?g";
str = str.replace(reg, "A");
console.log(str);

var reg = /\b/g;
var str2 = "hello world 123 >abc abc*"
str2 = str2.replace(reg, "A");
console.log(str2);
```

输出：

```
    AAA>AAAA|A
    A?A
    AhelloA AworldA A123A >AabcA AabcA*
```

常用元字符：

| 代码 | 说明 |
| ---- | ---- |
| . | 匹配除换行以外的人任意字符 |
| \w | 匹配字母、数字、下划线 等价于[a-zA-Z0-9_] |
| \W | 匹配任意非单词字符，等价于 |
| \s | 匹配空白符，例如空格、换行 |
| \S | 匹配非空白符 |
| \d | 匹配数字 |
| \D | 匹配非数字 |
| \b | 匹配单词的开始或结束 |
| ^ | 匹配字符串开始的地方，但在[]中表示取反 |
| $ | 匹配字符串的结束 |

#### []集合

`[abc]`的含义：目标字符串匹配abc中任意一个字符。  
`[^abc]`的含义：目标字符串匹配abc以外的任意字符。  
`[a-f0-9]`的含义：目标字符串匹配小写字母a到f和数字0到9

```js
    var reg1 = /[abc]/g;
var reg2 = /[^abc]/g;
var reg3 = /[a-e0-9]/g;
var str = "acdefg>?129";
console.log(str.replace(reg1, "A"));
console.log(str.replace(reg2, "A"));
console.log(str.replace(reg3, "A"));
```

输出：

```
    AAdefg>?129
    acAAAAAAAAA
    AAAAfg>?AAA
```

#### 出现次数

`[a]{2, 4}` : a出现2到4次