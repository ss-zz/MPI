/**
 * 渲染单项多列图，列数不定
 * @param datalist 数据源
 * @param entityCode 统计项（自动去重构建对象）
 * @param xAxisName X轴code
 * @param seriesIndex Y轴code
 * @param seriesNames Y轴中文名
 * @param divId 渲染目标id
 * @param type 渲染图形类型,可以是'area','column','line','spline','areaspline'
 * @param title 渲染图标题
 * @param ytitle Y轴单位
 */
function renderMultiEntityChart(datalist,entityCode, xAxisName, seriesIndex,seriesNames, divId, chartType , title,ytitle) {
	   var chartData = [],
	       chart = {},
	       xAxisCateg = [],
	       type = chartType || 'column';
	   if( type === 'pie'){
	   	   throw new Error('this func can not render a pie chart!');
	   }else{
		   //去除重复的机构名称
		   var ret = [], done = {};
		   ret_date=[], done_date = {};
		 $(datalist).each(function(i,Item){//获得实体列的所有值
			// alert(Item[entityCode]);
			 if(!done[Item[entityCode]]){
				 ret.push(Item[entityCode]);
				 done[Item[entityCode]] = true;
			 }
			 if(Item[xAxisName]!=null&&Item[xAxisName]!=""){
				 if(!done_date[Item[xAxisName]]){
					 ret_date.push(Item[xAxisName]);
					 done_date[Item[xAxisName]] = true;
				 }
			 }
		 });
		 ret_date.sort();
	   	 //指标列表
	     $(ret).each(function (org_i, org_item) {//遍历去除重复的统计指标
	   		var chartSeriesItem = {
	   				name:org_item,
	   				data:[]
	   		};
	   		$(ret_date).each(function (dt_i, dt_Item) {//遍历x轴
		   		$(datalist).each(function(dIdx,dItem){//遍历数据集，获取统计指标对应x轴各项的值，封装成 Highcharts 的chartData
		   			if(dItem[entityCode]==org_item){
		   			 	     if(dItem[xAxisName]==dt_Item){
		   			 	    	chartSeriesItem.data.push(dItem[seriesIndex]);
		   			 	     }
	   			     }
		   		});
		   		if(chartSeriesItem.data.length == dt_i ){
		   			chartSeriesItem.data.push(null);
		   		}
	   		});
	   		
	   		chartData.push(chartSeriesItem);
	   	});
	   	
	   	$(ret_date.sort()).each(function(dt_i,dt_item){//遍历去除重复的x轴项，存入 Highcharts 的xAxiscateg
	   		xAxisCateg.push(dt_item|| null);
		});
	   }
	   chart = new Highcharts.Chart({
	       chart: {
	           type: type,
	           renderTo: divId,
	           plotBackgroundColor: null,
	           plotBorderWidth: null,
	           plotShadow: false
	       },
	       title: {
	           text : title
	       },
	       credits : {// 不显示highchart标记
				enabled : false
	       },          
	       tooltip: {
	           formatter: function () {
	        	   return '<b>'+ this.series.name +'</b><br/>'+
	               this.x +': '+ this.y +ytitle;
	           }
	       },
	       xAxis:{
	       	title: {text:''},
	       	categories:xAxisCateg
	       },
	       yAxis:{
	    	   labels: {
	    		   y: 16,
					"formatter" : function() {return this.value + ytitle;}
				},
	    	min:0,
	       	title:{text:''}
	       },
	       series: chartData
	   });
	  svg=chart.getSVG();
	  /*$('#svg').val(svg);*/
	  /*alert(svg);*/
	}

/**
 * @param data 数据源，传入一个数据list，方法自动解析list，去除重复，将列变行
 * @param divId 渲染目标div
 * @param rowIndex 每一行第一列引用数据源的列
 * @param rowIndexHead 每一行第一列表头显示
 * @param tabHeadIndex 数据表头引用数据源的列
 * @param tabDataIndex 数据列引用数据源的列
 */
function renderHtmlTable(data,divId,rowIndex,rowIndexHead,tabHeadIndex,tabDataIndex) {
    var colIndexs=[],datalist=[];
    
    var ret = [], done = {},done_date = {};
	 $(data).each(function(i,Item){//获得实体列的所有值
		 if(!done[Item[rowIndex]]){
			 ret.push(Item[rowIndex]);
			 done[Item[rowIndex]] = true;
		 }
		 if(Item[tabHeadIndex]!=null&&Item[tabHeadIndex]!=""){
			 if(!done_date[Item[tabHeadIndex]]){
				 colIndexs.push(Item[tabHeadIndex]);
				 done_date[Item[tabHeadIndex]] = true;
			 }
		 }
	 });
	 colIndexs.sort();
	 //指标列表
  $(ret).each(function (org_i, org_item) {//遍历去除重复的统计指标
		var tabData = {
				name:org_item,
				data:{}
		};
		$(colIndexs).each(function (dt_i, dt_Item) {//遍历x轴
	   		$(data).each(function(dIdx,dItem){//遍历数据集，获取统计指标对应x轴各项的值，封装成 Highcharts 的chartData
	   			if(dItem[rowIndex]==org_item){
	   			 	     if(dItem[tabHeadIndex]==dt_Item){
	   			 	    	tabData.data[dt_Item] = dItem[tabDataIndex]|| '--';
	   			 	     }
			     }
	   		});
	   		if(tabData.data.length == dt_i ){
	   			tabData.data.push(null);
	   		}
		});
		datalist.push(tabData);
		  
  });
    if( ! divId ){
    	return;
    }
    $('#'+divId).children().remove();
    var jThead = $('<thead/>');
    var jHeadTr = $('<tr/>').appendTo(jThead);
    jHeadTr.append( $('<th/>').append(rowIndexHead));  
    for(var i=0;i<colIndexs.length;i++){      
        jHeadTr.append($('<th/>').append(colIndexs[i]) );
    }  
    var jTbody = $('<tbody/>');
    // 遍历数据集合,每次循环生成一个表格行
    $(datalist).each(function (di,item){
        var jBodyTr =  $('<tr/>').appendTo(jTbody);
        jBodyTr.append( $('<td/>').append(item.name));
        // 遍历数据列索引，每次生成一个单元格
        $(item.data).each(function (ii,iv) {
           $(colIndexs).each(function (dt_i, dt_Item) {//遍历x轴
        	   jBodyTr.append( $('<td/>').append(iv[dt_Item]));
   		});
        });
    });
    /* 生成导出功能数据和按钮开始 */
    var colNames = [], colIndexes = [],items=[];
    colNames.push(rowIndexHead);
    for(var i=0;i<colIndexs.length;i++){      
    	colNames.push((colIndexs[i]) );
    }
    colIndexes.push('name');
    for(var i=0;i<colIndexs.length;i++){      
    	colIndexes.push((colIndexs[i]) );
    }
    $(datalist).each(function (di,item){
    	var xitem = item.data;
    	xitem['name']= item.name;
    	items.push(xitem);
    });
    var json = {
    	'colNames':colNames,
    	'colIndexs':colIndexes,
    	'items':items
    };
    /* 绑定按钮导出事件 */
    $("#expBtn").click(function(){
    	var gridjson=JSON.stringify(json);
    	doexp(gridjson);
    });
    $('<table/>').addClass('gykTbl').append(jThead).append(jTbody).appendTo( $('#'+divId) );
}
