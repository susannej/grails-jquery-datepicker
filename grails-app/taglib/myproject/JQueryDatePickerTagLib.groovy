package myproject

class JQueryDatePickerTagLib {
    static defaultEncodeAs = [taglib:'html']
	static encodeAsForTags = [jqDatePicker: 'raw']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
	
	def jqDatePicker = {attrs, body ->
		def out = out
		def name = attrs.name //The name attribute is required for the tag to work seamlessly with grails
		def id = attrs.id ?: name
		def value = attrs.value
		def displayFormat = attrs.displayFormat
		def datepickerFormat = attrs.datepickerFormat
		
		//def displayFormatString = displayFormat ? displayFormat : "MM.dd.yyyy"
		def datepickerFormatString = datepickerFormat ? datepickerFormat : "dd.mm.yy"
		def dateString = value ? value.format("dd.MM.yyyy") : ""
		def dayString = value ? value.format('dd') : ""
		def monthString = value ? value.format('MM') : ""
		def yearString = value ? value.format('yyyy') : ""

		//Create date text field and supporting hidden text fields need by grails
		out.println "<input type=\"text\" name=\"${name}_dp\" id=\"${id}_dp\" value=\"${dateString}\" " 
		out.println " class=\"form-control datepicker\" data-dateformat=\"${datepickerFormatString}\" "
		if (attrs.placeholder != null) {
			out.println "placeholder=\"${attrs.placeholder}\" "
		}
		if (attrs.disabled != null) {
			out.println "disabled=\"${attrs.disabled}\" "
		}
		out.println " onchange=\"${name}_dp_droesel();\" "
		out.println "/>"
		out.println "<input type=\"hidden\" name=\"${name}\" id=\"${id}\" value=\"date.struct\" />"
		out.println "<input type=\"hidden\" name=\"${name}_day\" id=\"${id}_day\" value=\"${dayString}\" />"
		out.println "<input type=\"hidden\" name=\"${name}_month\" id=\"${id}_month\" value=\"${monthString}\" />"
		out.println "<input type=\"hidden\" name=\"${name}_year\" id=\"${id}_year\" value=\"${yearString}\" />"
		
		//Code to parse selected date into hidden fields required by grails
		out.println "<script type=\"text/javascript\">"
		out.println "function ${name}_dp_droesel() {"
		out.println "    if (\$(\"#${id}_dp\").val().length > 0) {"
		out.println "        try {"
		out.println "            var selDate = \$.datepicker.parseDate('${datepickerFormatString}', \$(\"#${name}_dp\").val());"
		out.println "            \$(\"#${name}_month\").attr(\"value\",selDate.getMonth() +1);"
		out.println "            \$(\"#${name}_day\").attr(\"value\",selDate.getDate());"
		out.println "            \$(\"#${name}_year\").attr(\"value\",selDate.getFullYear());"
		out.println "            \$(\"#${id}_dp\").css({'color': ''});"
		out.println "        } catch(e) {"
		out.println "            \$(\"#${id}_dp\").css({'color': '#FF0000'});"
		out.println "            \$(\"#${id}_dp\").focus();"
		out.println "            alert(e);"
		out.println "        }"
		out.println "    } else {"
		out.println "        \$(\"#${id}_dp\").css({'color': ''});"
		out.println "        \$(\"#${name}_month\").attr(\"value\",\"\");"
		out.println "        \$(\"#${name}_day\").attr(\"value\",\"\");"
		out.println "        \$(\"#${name}_year\").attr(\"value\",\"\");"
		out.println "    }"
		out.println "}"
		out.println "</script>"
		
	}

}
