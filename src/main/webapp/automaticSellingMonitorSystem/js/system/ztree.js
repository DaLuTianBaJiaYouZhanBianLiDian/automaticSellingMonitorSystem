var setting = {
	check: {
		enable: true,
		showRemoveBtn: false,
		showRenameBtn: false,
		chkboxType: {"Y":"", "N":""}
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
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	nodes = zTree.getCheckedNodes(true);
	selectNames = "";
	selectIds = "";
	for (var i=0, l=nodes.length; i<l; i++) {
		selectNames += nodes[i].name + "&nbsp;&nbsp;";
		selectIds += nodes[i].id + ",";
	}
//	if (selectNames.length > 0 ) selectNames = selectNames.substring(0, selectNames.length-1);
	var nameObj = $("#nameSel");
	nameObj.html(selectNames);
	
	if (selectIds.length > 0 ) selectIds = selectIds.substring(0, selectIds.length-1);
	console.log(selectIds);
	var idObj = $("#selectIds");
	idObj.val(selectIds);
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
function initRoleFunctionTree(roleId, from){
	$.post(appbasepath + "/systemuser/getFunctions4Role?roleId="+ roleId +"&time=" + (new Date().getTime()), function(result) {
		if (result.status == 'succeed') {
			zNodes = result.zNodes;
			var nodeNames = new Array();
			var nodeIds="";
			if(zNodes != null && zNodes.length>0){
				for(var i=0; i<zNodes.length; i++){
					if(zNodes[i].checked == true){
						var nodeName = zNodes[i].name + "&nbsp;&nbsp;";
						nodeNames.push(nodeName);
						nodeIds += zNodes[i].id + ",";
					}
				}
			}
			var nameObj = $("#nameSel");
			nameObj.html(nodeNames);
			var nameObj_view = $("#nameSel_view");
			nameObj_view.html(nodeNames);
			if (nodeIds.length > 0 ) nodeIds = nodeIds.substring(0, nodeIds.length-1);
			console.log(nodeIds);
			var idObj = $("#selectIds");
			idObj.val(nodeIds);
			if(from != "view"){
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			}
		}
	});
};
