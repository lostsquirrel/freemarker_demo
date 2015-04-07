<#escape x as x?html>
<h1>hello ${user}</h1>
<@repeat count=3 hr=true; cnt>
 ${cnt}. Test
</@repeat>
</#escape>