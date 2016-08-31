# grails-jquery-datepicker
a simple taglib for using the jquery datepicker with grails

It consist of a new tag, which creates the grails-datepicker fields in the background and converts the input from the jquery datepicker field to the grails input fields.

No additional handling in the controller ist required.

**Example:**

&lt;g:jqDatePicker name="myDate" precision="day"  value="${myDomainClassInstance?.myDate}" required="" /&gt;
