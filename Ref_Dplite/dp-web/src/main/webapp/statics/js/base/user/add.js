/**
 * 新增-用户管理js
 */
$(function(){
	vm.getRoleList();
})
var vm = new Vue({
	el:'#dpLTE',
	data: {
		user:{
			status: 1,
			roleIdList:[]
		}
	},
	methods : {
		getRoleList: function(){
            $('.roleSelect').selectBindEx({
                url: '../../sys/role/select?_' + $.now(),
                placeholder: '请选择角色',
                value: 'roleId',
                text: 'roleName'
            });
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    vm.user.roleIdList = $('.roleSelect').val();
		    $.SaveForm({
		    	url: '../../sys/user/save?_' + $.now(),
		    	param: vm.user,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
