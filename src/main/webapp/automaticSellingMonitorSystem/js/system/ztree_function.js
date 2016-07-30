var setting = {
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "all"
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		beforeClick: beforeClick,
		onCheck: onCheck
	}
};

function beforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeMenu");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeMenu");
	nodes = zTree.getCheckedNodes(true);
	selectName = "";
	selectId = "";
	for (var i=0, l=nodes.length; i<l; i++) {
		selectName += nodes[i].name;
		selectId += nodes[i].id + ",";
	}
	var nameObj = $("#nameMenuSel");
	nameObj.val(selectName);
	console.log(selectName);
	
	if (selectId.length > 0 ) selectId = selectId.substring(0, selectId.length-1);
	console.log(selectId);
	var idObj = $("#super_id");
	idObj.val(selectId);
}
function showMenu() {
	$('.menuContent').toggle();
}

//var zNodes =[
//             {id:1, pId:0, name:"Beijing"},
//             {id:2, pId:0, name:"Tianjin"},
//             {id:3, pId:0, name:"Shanghai"},
//             {id:6, pId:0, name:"Chongqing"},
//             {id:4, pId:0, name:"Hebei Province", open:true, drag:false},
//             {id:41, pId:4, name:"Shijiazhuang"},
//             {id:42, pId:4, name:"Baoding"},
//             {id:43, pId:4, name:"Handan"},
//             {id:44, pId:4, name:"Chengde"},
//             {id:5, pId:0, name:"Guangdong Province", open:true},
//             {id:51, pId:5, name:"Guangzhou"},
//             {id:52, pId:5, name:"Shenzhen"},
//             {id:53, pId:5, name:"Dongguan"},
//             {id:54, pId:5, name:"Fushan"},
//             {id:6, pId:0, name:"Fujian Province", open:true},
//             {id:61, pId:6, name:"Fuzhou"},
//             {id:62, pId:6, name:"Xiamen"},
//             {id:63, pId:6, name:"Quanzhou"},
//             {id:64, pId:6, name:"Sanming"},
//             {id:65, pId:64, name:"Sanming"}
//             ];
var zNodes ;
function initFunctionTree(functionId){
	$.post(appbasepath + "/systemuser/getFunctions4Menu?functionId="+ functionId +"&time=" + (new Date().getTime()), function(result) {
		if (result.status == 'succeed') {
			zNodes = result.zNodes;
			console.log("zNodes : "+zNodes);
			var nodeName = "";
			var nodeId="";
			if(zNodes != null && zNodes.length>0){
				for(var i=0; i<zNodes.length; i++){
					if(zNodes[i].checked == true){
						nodeName = zNodes[i].name;
						nodeId += zNodes[i].id;
					}
				}
			}
			var nameObj = $("#nameMenuSel");
			nameObj.val(nodeName);
			console.log(nodeName);
			console.log(nodeId);
			var idObj = $("#super_id");
			idObj.val(nodeId);
			$.fn.zTree.init($("#treeMenu"), setting, zNodes);
		}
	});
};
