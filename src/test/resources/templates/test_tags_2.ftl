<#-- Now you can't use <#macro upper>, but instead you can: -->
<#assign upper = "freemarker_demo.test.tags.UpperDirective"?new()>  

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

----shared variable-----
${company}