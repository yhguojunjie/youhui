//--------------------显示页码部分开始-----------
	
//1、定义当前页 	currentPage 初始化取值为1
//2、定义开始页  startPage 
//3、定义结束页（结束页） endPage 值和4一样
//4、定义总页数  totalPage  
	
function initPages(currentPage,startPage,endPage,totalPage) {
	
	/*	alert("totalPage="+totalPage);
		alert("currentPage="+currentPage);
		alert("startPage="+startPage);
		alert("endPage="+endPage);*/
			
		$("#pageNum").empty();
		//当总页数大于11的时候，设定起始页和结束页
		if (totalPage > 11) {
			//要设定起始页
			if (currentPage - 10 > 0) {
				//如果当前页-10>0则设置起始页=当前页-10，否则不设置，startPage=1
				startPage = currentPage - 10;
			}
			//设定结束页
			if (currentPage + 9 < totalPage) {
				//如果当前页+9>总页数，则设定结束页为   当前页+9，否则设定结束页=总页数
				endPage = currentPage + 9;
			} else {
				endPage = totalPage;
			}
		}
		
		var pageNumStr = "";
	//	pageNumStr += '<div class="tr pages">';
		//判断什么时候显示上一页
		if(currentPage != 1) {
			pageNumStr += "<a href='javascript:getPageDatas(" + (currentPage - 1)+ ")' class='prev'></a>";
		}
	
		//for循环读出页码
		if(totalPage > 1){
			
			for ( var i = startPage; i <= endPage; i++) {
			//判断是否是当前页，如果是当前页，让该页码加粗或变红
			if (currentPage == i) {
				//如果是当前页，设置加粗、红色显示
				pageNumStr += "&nbsp;<a class='active'><b>" + i + "</b></a>";
			} else {
				pageNumStr += "&nbsp;<a href='javascript:getPageDatas(" + i
							+ ")'>" + i + "</a>";
			}
			}
		}
	
		//判断什么时候显示下一页
		if (currentPage < totalPage) {
	
			pageNumStr += "<a href='javascript:getPageDatas(" + (currentPage + 1)
						+ ")' class='next'></a>";
			}
		
	//	pageNumStr += '</div>';
		$("#pageNum").append(pageNumStr);
	
	}
//--------------------显示页码部分结束-----------