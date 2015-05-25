!function ($) {

    /* PrettyCode CLASS DEFINITION
     * ========================= */
    var PrettyCode = function (element, codeUrl) {
        
        this.codeUrl = codeUrl;

        // this.$codePanel = $('<pre class="prettyprint linenums">' +
        //         '<span class="loading">源代码加载中...</span></pre>');
        this.$codeContainer = $(element);
        this.$codeContainer.append('<span class="loading">源代码加载中...</span>');
    };

    PrettyCode.prototype = {
        constructor : PrettyCode,
        htmlEncode : function (s) {
            return s.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
        },
        show : function () {
			var noContent = this.$codeContainer.children().hasClass('loading');
			if (noContent) {
				var that = this;
				$.ajax({
					url : this.codeUrl,
					dataType : 'text',
					success : function (data) {
						var encodedData = that.htmlEncode(data);

						that.$codeContainer.html(encodedData);
						window.prettyPrint && prettyPrint();
					},
					error : function (XMLHttpRequest, textStatus, errorThrown) {
						alert(errorThrown);
					},
					cache : true,
					type : 'get',
					async : true
				});
			}
        }
    };

    /* PrettyCode PLUGIN DEFINITION
     * ============================= */
    $.fn.prettyCode = function (option) {
        return this.each(function () {
            var $this = $(this),
            data = $this.data('prettyCode'),
            codeUrl = typeof option == "string" && option;

            if (!data)
                $this.data('prettyCode', data = new PrettyCode(this, codeUrl));

            data.show();
        });
    };

    /* PrettyCode DATA-API
     * =================== */
    $(window).on('load', function () {
        $('[data-code-url]').each(function () {
            var $this = $(this),
            codeUrl = $this.attr('data-code-url');
            $this.prettyCode(codeUrl);
        })
    });
}(window.jQuery);

