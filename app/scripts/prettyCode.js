+function ($) {
  'use strict';

    /* PrettyCode CLASS DEFINITION
    * ========================= */
    var PrettyCode = function (element, codeUrl) {
      this.codeUrl = codeUrl;
      this.$codeContainer = $(element);
      this.$codeContainer.html('<span class="loading">源代码加载中...</span>');
    };

    PrettyCode.prototype.htmlEncode = function (s) {
      return s.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
    };

    PrettyCode.prototype.show = function () {
      var noContent = this.$codeContainer.children().hasClass('loading');
      if (noContent) {
       var that = this;
       $.ajax({
        url : this.codeUrl,
        dataType : 'text',
        success : function (data) {
         var encodedData = that.htmlEncode(data);
         that.$codeContainer.html(window.prettyPrintOne(encodedData, '', 'prettyPrintOne'));
       },
       error : function (XMLHttpRequest, textStatus, errorThrown) {
         that.$codeContainer.html(errorThrown);
       },
       cache : true,
       type : 'get',
       async : true
     });
     }
   };

    /* PrettyCode PLUGIN DEFINITION
    * ============================= */
    function Plugin(option) {
      return this.each(function () {
        var $this = $(this),
        data = $this.data('prettyCode'),
        codeUrl = typeof option == "string" && option;

        if (!data)
          $this.data('prettyCode', data = new PrettyCode(this, codeUrl));

        data.show();
      });
    }

    var old = $.fn.prettyCode;

    $.fn.prettyCode = Plugin;
    $.fn.prettyCode.Constructor = PrettyCode;

    // PrettyCode NO CONFLICT
    // =================
    $.fn.prettyCode.noConflict = function () {
      $.fn.prettyCode = old;
      return this;
    };

    /* PrettyCode DATA-API
    * =================== */
    
    $(function(){
      var $codeContainer = $('code[data-code-url]'),
      $window = $(window);

      $window.on("scroll.prettyCode", function(){
        $codeContainer.each(function () {
          var $this = $(this),   
          codeUrl = $this.attr('data-code-url');

          if(!$this.data('loaded')) {
            var fold= (window.innerHeight ? window.innerHeight : $window.height()) + $window.scrollTop();

            if(fold >= $this.offset().top + 10) {
              $this.data('loaded', true);
              $this.prettyCode(codeUrl);
            }
          }
        });


      });
    });

  }(jQuery);