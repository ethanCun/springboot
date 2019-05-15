#### home.html
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>home</title>
</head>
<body>

<pre>
    标准表达式语法
    它们分为四类：

    1.变量表达式 ${}
    2.选择或星号表达式 *{}
    3.文字国际化表达式 #{}
    4.URL 表达式 @{}
</pre>

<!--th:text-->
<p th:text="${name}"></p>

<!--字符串拼接-->
<p th:text="'${}'"></p>
<p th:text="'welcome,' + ${name}"></p>

<!--th:src-->
<p th:text="'th:src'"></p>
<img th:src="@{/img/Piglogo1024.png}" th:style="'width:100px; height:100px; display:block'">

<!--th:href-->
<p th:text="'th:href'"></p>
<a th:href="@{http://www.baidu.com}" th:text="baidu"></a>
<a th:href="@{/test1}" th:text="text1.html"></a>

<!--th:if 只有表达式中的条件成立，才会显示其内容-->
<p th:text="'th:if'"></p>
<p th:if="${name == 'czy'}" th:text="${name}"></p>

<!--th:unless 只有表达式中的条件不成立，才会显示其内容-->
<p th:text="'th:unless'"></p>
<p th:unless="${name == null && name.equals('')}" th:text="${name}"></p>

<!--th:object:替换对象-->
<p th:text="'th:object'"></p>
<div th:object="${names}"></div>

<!--th:each-->
<!--
iterStat称作状态变量，属性有：
index:当前迭代对象的 index（从0开始计算）
count: 当前迭代对象的 index(从1开始计算)
size:被迭代对象的大小
current:当前迭代变量
even/odd:布尔值，当前循环是否是偶数/奇数（从0开始计算）
first:布尔值，当前循环是否是第一个
last:布尔值，当前循环是否是最后一个
-->
<p th:text="'th:each'"></p>
<tr th:each="name,iterStat: ${names}">

    <td th:text="'name:' + ${name}"></td>
    <td th:text="'index:' + ${iterStat.index}"></td>
    <td th:text="'count:' + ${iterStat.count}"></td>
    <td th:text="'current:' + ${iterStat.current}"></td>
    <td th:text="'size:' + ${iterStat.size}"></td>
    <td th:text="'first:' + ${iterStat.first}"></td>
    <td th:text="'last:' + ${iterStat.last}"></td>
    <td th:text="'odd:' + ${iterStat.odd}"></td>

    <br/>
</tr>

<!--使用*号表达式-->
<div th:each="user, iterStat : ${user}">

    <p th:text="*{name}"></p>
    <p th:text="*{age}"></p>
    <p th:text="${iterStat.index}"></p>
    <p th:text="${iterStat.current}"></p>
</div>

<!--th:onclick-->
<p th:text="'th:onclick'"></p>
<p th:onclick="testOnclick()" th:text="onclick"></p>
<script th:src="@{/js/onclick.js}"></script>

<p class="testP" th:text="'th:onclick测试p标签'"></p>

<!--th:include:布局标签，替换内容到引入的文件-->
<p th:text="'th:include:布局标签，替换内容到引入的文件 th:replace	布局标签，替换整个标签到引入的文件'"></p>

<pre>
    其中th:include中的参数格式为templatename::[domselector],
    其中templatename是模板名（如footer），domselector是可选的dom选择器。
    如果只写templatename，不写domselector，则会加载整个模板。
</pre>
<div th:include="footer :: copyright"></div>

<!--不通过th:fragment饮用模板-->
<div th:include="footer :: .copyright1"></div>
<div th:include="footer :: #copyright2"></div>

<!--th:replace:布局标签，替换整个标签到引入的文件-->
<p th:text="'th:include 是加载模板的内容，而th:replace则会替换当前标签为模板中的标签'"></p>
<div th:replace="footer :: copyright"></div>

<!--内联js-->
<p th:text="'内联js'"></p>
<pre>
    内联文本：[[#{name}]] 内联文本的表示方式，使用时，必须先用th:inline="text/javascript/none"激活，
    th:inline可以在父级标签内使用，甚至作为 body 的标签。内联文本尽管比th:text的代码少，不利于原型显示。
</pre>
<script th:inline="javascript">

    var ctx = [[@{/}]];
    var userUrl = ctx + "user";
    var name = [[${name}]];
    var names = [[${names}]];
    var user = [[${user}]];

    console.log("name = " + name);
    console.log("names = " + names);
    console.log("user = " + user);
    console.log("ctx = " + ctx);
    console.log("userUrl = " + userUrl);

</script>


<!--themeleaf内嵌变量-->
<p th:text="${#dates.createNow()}"></p>
<p th:text="${#dates.createToday()}"></p>
<p th:text="${#strings.isEmpty('czy')}"></p>
<p th:text="${#strings.length('czy')}"></p>
<p th:text="${#strings.randomAlphanumeric(10)}"></p>

</body>
</html>
```
#### onclick.js
```
function testOnclick() {

    var x = document.getElementsByClassName('testP');

    /**
     *  ==：运算符称作相等，用来检测两个操作数是否相等，这里的相等定义的非常宽松，可以允许进行类型转换
     ===：用来检测两个操作数是否严格相等

     1、对于string,number等基础类型，==和===是有区别的
     不同类型间比较，==之比较“转化成同一类型后的值”看“值”是否相等，===如果类型不同，其结果就是不等
     同类型比较，直接进行“值”比较，两者结果一样

     2、对于Array,Object等高级类型，==和===是没有区别的

     3、基础类型与高级类型，==和===是有区别的
     对于==，将高级转化为基础类型，进行“值”比较，因为类型不同，===结果为false
     */

    if (x[0].innerHTML === 'xxxx'){

        x[0].innerHTML = 'yyyy';
        x[0].style.color = 'red';
    } else {

        x[0].innerHTML = 'xxxx';
        x[0].style.color = "green";
    }
}
```
#### test1.html
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>test1</title>
</head>
<body th:style="'background:red'">

</body>
</html>
```
#### footer.html
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>footer</title>
</head>
<body>

<!--th:fragment	布局标签，定义一个代码片段，方便其它地方引用-->
<p>th:fragment	布局标签，定义一个代码片段，方便其它地方引用</p>
<div th:fragment="copyright">

    @2019 czy
</div>

<div class="copyright1">

    @2019 ethan1
</div>

<div id="copyright2">

    @2019 ethan2
</div>

</body>
</html>
```