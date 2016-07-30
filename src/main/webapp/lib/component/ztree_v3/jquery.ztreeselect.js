/**
 * 基于ztree的树形选择控件
 * 这是一个比较简单的树形选择控件，不适合复杂树的情况
 */
(function ($) {

/*
<div class="chosen-container chosen-container-single chosen-container-single-nosearch" style="width: 59px;width: 100%;" title="" id="assetType_chosen">
 <a class="chosen-single" tabindex="-1"><span>物理</span><div><b></b></div></a>
 <div class="chosen-drop">
  <div class="chosen-search">
   <input type="text" autocomplete="off" readonly=""></div>
   <ul class="chosen-results">
    <li class="active-result result-selected" data-option-array-index="0" style="">物理</li>
    <li class="active-result" data-option-array-index="1" style="">虚拟</li>
    </ul>
    </div></div>
*/

    $.fn.ztreeselect = function(options){
    	// 目前 所能配置的参数
    	var defaults ={
    		ajaxsource:	null,
    		datasource: [],
    		async:false,
    		multiple:false,
    		expandAll:false,
    		options:null,
    		datavalue:null,
            Y:"",// 选中 "p" 代表关联父节点 "s"代表关联子节点 "ps"  代表关联父子节点
            N:"",// 取消
            Yreverse:false,
    		onChange:function(id,v){}
    	}
    	var options = $.extend(defaults, options);

        this.each(function(){
            var thisjq=$(this);
            var datavalue = thisjq.attr("data-value")?thisjq.attr("data-value"):options.datavalue;
            //var datavalue = "中国,北京";
            var datavalues = [];
            var isTreeExist = false;
            var treeExist = isTreeExist;
            if(datavalue){
            	datavalues = datavalue.split(",");
            }
            /*
             * 生成界面结构 
             * 目前样式都是写在 style下的 ，如果以后想修改的话，需要修改这个js （这个需要修改）
             */
            var thisjqid = thisjq.attr("id")?thisjq.attr("id"):uuid(7);
            var _treeObj = $.fn.zTree.getZTreeObj(thisjqid+"_tree");if(_treeObj){treeExist =isTreeExist=true;}
            var treeselectInputKeyId = thisjq.is('input')?thisjqid:thisjqid+"_inputkey";
            var treeselectInputId = thisjqid+"_inputval";
            var treeselectBtnId = thisjqid+"_btn";
            var treeselectDivId = thisjqid+"_div";
            var treeselectTreeId = thisjqid+"_tree";
            
            if(!isTreeExist){
	            var _treeselect = $('<div style="position:relative"></div>');            

        //<button class="btn btn-default" type="button">Go!</button>
      
	            //var _treeselectInput = $('<div class="input-group"><input id="'+treeselectInputKeyId+'" type="hidden" /><input id="'+treeselectInputId+'" type="text"  value=""/>  <span class="input-group-btn"><button type="button" class="btn btn-default" id="'+treeselectBtnId+'" tabindex="-1"><span class="caret"></span></button></span></div>');
                var _treeselectInput = $(' <div id="'+treeselectBtnId+'" class="chosen-container chosen-container-single chosen-container-single-nosearch" style="width: 100%;" >'+
                                         '<input id="'+treeselectInputKeyId+'" type="hidden" />'+
                                         //'<input id="'+treeselectInputId+'" type="text"  value=""/>'
                                         '<a class="chosen-single" tabindex="-1"><span id="'+treeselectInputId+'"></span>'+
                                         '<div><b></b></div></a></div>');

 

	            _treeselect.append(_treeselectInput);
	            var _treeselectContain  = $('<div id="'+treeselectDivId+'" class="menuContent" style="display:none; position: absolute;z-index: 1000;">'+
	        			'<ul id="'+treeselectTreeId +'" class="ztree" style="border: 1px solid #617775;background: #f0f6e4;overflow-y: scroll;overflow-x: auto;margin-top:0; width:180px; height: 300px;"></ul>'+
	        			'</div>'
	            		);
	            _treeselect.append(_treeselectContain);
            }else{
	            var _treeselectInput = $('#'+treeselectInputKeyId).parent();
	            var _treeselectContain  = $('#'+treeselectDivId);
	           
	            $.fn.zTree.destroy(treeselectTreeId);
	            treeExist = false;
            }
            
            /*
             * 开始生成树
             */
            var setting = {
    				view: {
    					dblClickExpand: false
    				},
    				data: {
    					simpleData: {
    						enable: true
    					}
    				},
    				callback: {
    					//onClick: onClick,
    					//onCheck: onCheck
    				}
    		};
            if(options.multiple){
            	setting.check = {
        				enable: true,
                        Yreverse:options.Yreverse,
        				chkboxType: {"Y":options.Y, "N":options.N}
        			};
            	setting.callback.beforeCheck = function(treeId, treeNode) {
        			var zTree = $.fn.zTree.getZTreeObj(treeselectTreeId);
                    debugger;
                    if(treeNode.name=="请选择"){
                        if(!treeNode.checked){
                            zTree.checkAllNodes(false);
                        }else{

                        }
                        //return false;
                    }else{
                        var nullnodes = zTree.getNodesByParam("name", "请选择", null);
                        zTree.checkNode(nullnodes[0], false, null, false);
                        //zTree.checkNode(treeNode, !treeNode.checked, null, true);
                    }
        			
        			//return false;  
        		}
        		
            	setting.callback.onCheck = function(e, treeId, treeNode) {
                    var zTree = $.fn.zTree.getZTreeObj(treeselectTreeId);
        			var nodes = zTree.getCheckedNodes(true),
                    v = "",id ="";
        			for (var i=0, l=nodes.length; i<l; i++) {
        				v += nodes[i].name + ",";
        				id += nodes[i].id + ",";
        			}
        			if (v.length > 0 ) {v = v.substring(0, v.length-1);}
        			if (id.length > 0 ) {id = id.substring(0, id.length-1);}
        			var treeselect = $("#"+treeselectInputId);
        			//treeselect.val(v); 
                    treeselect.text(v);
        			$("#"+treeselectInputKeyId).val(id);
        			//oTable.fnFilter( v, index );
        			options.onChange(id,v);
        		}
            }else{
                setting.callback.beforeClick = function (e, treeId, treeNode) {
                	}
        	    	setting.callback.onClick = function (e, treeId, treeNode) {
        				var zTree = $.fn.zTree.getZTreeObj(treeselectTreeId),
        				nodes = zTree.getSelectedNodes(true),
        				v = "",id ="";
        				for (var i=0, l=nodes.length; i<l; i++) {
        					v += nodes[i].name + ",";
        					id += nodes[i].id + ",";
        				}
        				if (v.length > 0 ) v = v.substring(0, v.length-1);
        				if (id.length > 0 ) {id = id.substring(0, id.length-1);}
        				var treeselect = $("#"+treeselectInputId);
        				//treeselect.val(v);
                        treeselect.text(v);
        				$("#"+treeselectInputKeyId).val(id);
        				//oTable.fnFilter( v, index );
        				options.onChange(id,v);
        			}  
            }
            if(options.ajaxsource&&$.trim(options.ajaxsource)!=""){
            	var async = options.async?options.async:false;
            	if(async){
    	        	setting.async = {
    	        			enable: true,
    	    				url:options.ajaxsource,
    	    				autoParam:["id"]
    	        	}
            	}else{
    	        	setting.async = {
    	        			enable: true,
    	    				url:options.ajaxsource
    	        	}       		
            	}

            }else{
            	var datasource = (options.datasource&&(options.datasource instanceof Array))?options.datasource:[];
//            	datasource =[
//    			{id:1, pId:0, name:"北京"},
//    			{id:2, pId:0, name:"天津"},
//    			{id:3, pId:0, name:"上海"},
//    			{id:6, pId:0, name:"重庆"},
//    			{id:4, pId:0, name:"河北省",  nocheck:true},
//    			{id:41, pId:4, name:"石家庄"},
//    			{id:42, pId:4, name:"保定"},
//    			{id:43, pId:4, name:"邯郸"},
//    			{id:44, pId:4, name:"承德"},
//    			{id:5, pId:0, name:"广东省",  nocheck:true},
//    			{id:51, pId:5, name:"广州"},
//    			{id:52, pId:5, name:"深圳"},
//    			{id:53, pId:5, name:"东莞"},
//    			{id:54, pId:5, name:"佛山"},
//    			{id:6, pId:0, name:"福建省", nocheck:true},
//    			{id:61, pId:6, name:"福州"},
//    			{id:62, pId:6, name:"厦门"},
//    			{id:63, pId:6, name:"泉州"},
//    			{id:64, pId:6, name:"三明"}
//    		 ];
            }
            //_treeselectInput.find("#"+treeselectInputId).click(function(){showTree();});
            _treeselectInput.unbind( "click" ).click(function(evt){
                // if (evt && evt.type === "mousedown") {
                //   evt.preventDefault();
                // }
                if(_treeselectContain.css("display")=="none"){
                    showTree();
                }else{
                    hideTree();
                }
                evt.stopPropagation(); 
            });
    			var showTree = function(){
    				//var treeselectJQ = treeSelectInput.find("#"+treeselectId);
    				//var treeselectJQOffset = treeselectJQ.offset();
    				//treeselectDiv
    				//treeSelectInput.find("#"+treeselectDiv).css({left:treeselectJQOffset.left + "px", top:treeselectJQOffset.top + treeselectJQ.outerHeight() + "px"}).slideDown("fast");
    				_treeselectContain.slideDown("fast");
    				$("body").bind("mousedown."+thisjqid, onBodyDown);
    			}
    			var hideTree = function () {
    				_treeselectContain.fadeOut("fast");
    				$("body").unbind("mousedown."+thisjqid, onBodyDown);
    			}
    			 var onBodyDown =  function(event) {
    				if (!( event.target.id == treeselectDivId || $(event.target).parents("#"+treeselectDivId).length>0)) {
    					hideTree();
    				}
    			}
    			//$.fn.zTree.init(treeSelectInput.find("#"+treeselectTree), setting, zNodes);
    			 if(options.ajaxsource&&options.async==false){
    				 setting.callback.onAsyncSuccess = function(){
	    				var zTree = $.fn.zTree.getZTreeObj(treeselectTreeId);
                        var newNode = {name:"请选择",id:""};
                        newNode = zTree.addNodes(null, newNode);
                        var nodes = zTree.getNodes();
                        zTree.moveNode(nodes[0], nodes[nodes.length-1], "prev");
	    				 
	    				 
                         if(options.expandAll){zTree.expandAll(true);}
	    				 var selectNodes = [];
	    				 var selectnames = "";
                         if(datavalues.length>0){
    	    				 for(var i=0;i<datavalues.length;i++){
    	    					 var node = findOneNodeById(nodes,datavalues[i]);
    	    					 // var node = findOneNodeByName(nodes,datavalues[i]);
    	    					 selectNodes.push(node);
    	    					 selectnames += node.name+",";
                                }
    	    				 if (selectnames.length > 0 ) {selectnames = selectnames.substring(0, selectnames.length-1);}
                         }else{
                            selectNodes.push(nodes[0]);
                            selectnames = nodes[0].name;
                         }
	    				 //var node = findOneNodeByName
	    				 if(options.multiple){
	    					 for(var j=0;j<selectNodes.length;j++){
	    						 //zTree.selectNode(selectNodes[j],true);
	    						 zTree.checkNode(selectNodes[j], true);
	    						 }
	    				 }else{
	    					 if(selectNodes.length>0){zTree.selectNode(selectNodes[0],false);}
	    				 }
	    				 _treeselectInput.find("#"+treeselectInputKeyId).val(datavalue);
	    				 //_treeselectInput.find("#"+treeselectInputId).val(selectnames);
                         _treeselectInput.find("#"+treeselectInputId).text(selectnames);
    			 	}
    			 }
    			 if(!treeExist){
	    			 if(datasource){
	    				 $.fn.zTree.init(_treeselectContain.find("#"+treeselectTreeId),setting, datasource);
	    			 }
	    			 else{
	    				 $.fn.zTree.init(_treeselectContain.find("#"+treeselectTreeId), setting);
	    			 } 
    			 }else{
    				 
    				 
//    				 var zTree = $.fn.zTree.getZTreeObj(treeselectTreeId);
//    				 
//    				 $.extend(zTree.setting.async,setting.async);
//    				 zTree.refresh();

//    				 var nodes = zTree.getNodes();
//    				 if (nodes.length>0) {
//    					 alert(nodes[0].name);
//    				 	treeObj.reAsyncChildNodes(nodes[0], "refresh",false);
//    				 }

    			 }
    			 if(!options.ajaxsource){
    				 var zTree = $.fn.zTree.getZTreeObj(treeselectTreeId);
                     var newNode = {name:"请选择",id:""};
                     newNode = zTree.addNodes(null, newNode);
                     var nodes = zTree.getNodes();
                     zTree.moveNode(nodes[0], nodes[nodes.length-1], "prev");
    				 if(options.expandAll){zTree.expandAll(true);}
    				 if(datavalues){
	    				 //var nodes = zTree.getNodes();

	    				 var selectNodes = [];
	    				 var selectnames = "";
                         if(datavalues.length>0){
                             for(var i=0;i<datavalues.length;i++){
                                 var node = findOneNodeById(nodes,datavalues[i]);
                                 // var node = findOneNodeByName(nodes,datavalues[i]);
                                 selectNodes.push(node);
                                 selectnames += node.name+",";
                                }
                             if (selectnames.length > 0 ) {selectnames = selectnames.substring(0, selectnames.length-1);}
                         }else{
                            selectNodes.push(nodes[0]);
                            selectnames = nodes[0].name;
                         }
	    				 for(var i=0;i<datavalues.length;i++){
	    					 var node = findOneNodeById(nodes,datavalues[i]);
	    					 //var node = findOneNodeByName(nodes,datavalues[i]);
	    					 selectNodes.push(node);
	    					 selectnames += node.name+",";
	    				 }
	    				 if (selectnames.length > 0 ) {selectnames = selectnames.substring(0, selectnames.length-1);}
	    				 //var node = findOneNodeByName
	    				 if(options.multiple){
	    					 for(var j=0;j<selectNodes.length;j++){
	    						 //zTree.selectNode(selectNodes[j],true);
	    						 zTree.checkNode(selectNodes[j], true);
	    						 }
	    				 }else{
	    					 if(selectNodes.length>0){zTree.selectNode(selectNodes[0],false);}
	    				 }
	    				 _treeselectInput.find("#"+treeselectInputKeyId).val(datavalue);
	    				 //_treeselectInput.find("#"+treeselectInputId).val(selectnames);
                         _treeselectInput.find("#"+treeselectInputId).text(selectnames);
    				 }
    			 }
    			 if(!isTreeExist){
	    			 if(thisjq.is('input')){
	    				 //thisjq.attr() 
	    				 //$('.inner').wrapAll('<div class="new" />');
	    				 var hiddenInput = _treeselectInput.find("#"+treeselectInputKeyId);
	    				 hiddenInput.attr("name",thisjq.attr("name"));
	    				 $.each(thisjq[0].attributes,function(i,n){
	    					 var name = n.name;
	    					 var value = n.value;
	    					 if(name == 'class' || name.indexOf('data-rule') != -1){
	    						 hiddenInput.attr(name,value);	
	    					 }
	    				 });
	    				 thisjq.replaceWith(_treeselect);
	    			 }else{
	    				 thisjq.append(_treeselect);
	    			 }
    			 }
		    	var data = {
		        		setValue:function(){
		        			
		        		},
		        		getValue:function(){
		        			//return $("#"+treeselectInputId).val();
                            return $("#"+treeselectInputId).text()
		        		}
		        	}
		    	thisjq.data("treeselect",data)
        });
       // $.fn.ztreeselect.get
        var CHARS = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
        var uuid = function (len, radix) {
            var chars = CHARS, uuid = [], i;
            radix = radix || chars.length;
            if (len) {
              // Compact form
              for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
            } else {
              // rfc4122, version 4 form
              var r;
              // rfc4122 requires these characters
              uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
              uuid[14] = '4';
              // Fill in random data.  At i==19 set the high bits of clock sequence as
              // per rfc4122, sec. 4.1.5
              for (i = 0; i < 36; i++) {
                if (!uuid[i]) {
                  r = 0 | Math.random()*16;
                  uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
                }
              }
            }
            return uuid.join('');
          };
        var findOneNodeByName = function(nodes,name){
        	for(var i=0;i<nodes.length;i++){
        		if(nodes[i].name===name){
        			return nodes[i];
        		}else if(nodes[i].children&&nodes[i].children.length>0){
        			var node = findOneNodeByName(nodes[i].children,name);
                    if (node) {
                        return node;
                    }
        		}
        	}
        	return null;
        };

        var findOneNodeById = function(nodes,Id){
        	for(var i=0;i<nodes.length;i++){
        		if(nodes[i].id==Id){
        			return nodes[i];
        		}else if(nodes[i].children&&nodes[i].children.length>0){
        			var node = findOneNodeById(nodes[i].children,Id);
                    if (node) {
                        return node;
                    }
        		}
        	}
            return null;
        };
    };
})(jQuery);
