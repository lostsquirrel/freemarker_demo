foo
<@upper>
  bar
  <#-- All kind of FTL is allowed here -->
  <#list ["red", "green", "blue"] as color>
    ${color}
  </#list>
  baaz
</@upper>
wombat 

----repeat-----
<#assign x = 1>

<@repeat count=4>
  Test ${x}
  <#assign x = x + 1>
</@repeat>

<@repeat count=3 hr=true>
  Test
</@repeat>

<@repeat count=3; cnt>
  ${cnt}. Test
</@repeat>  

----shared variable-----
${company}
