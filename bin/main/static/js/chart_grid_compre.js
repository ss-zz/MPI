var colors = Highcharts.getOptions().colors;
/**
 * 生成html表格
 * 
 * @param data
 *            数据列表,如[{name:'张三',age:23},{name:'李四'，age:45}]
 * @param header
 *            列名 ['姓名','年龄']
 * @param index
 *            列索引 ['name','age']
 * @param divId
 *            目标Id
 */
function renderComTableink(datalist, header, index, divId) {
    "use strict";
    if( ! divId ){
    	return;
    }
    $('#'+divId).children().remove();
    if( ! header || ! index ){
        return;
    }
    var jThead = $('<thead/>');
    var jHeadTr = $('<tr/>').appendTo(jThead);
    $(header).each(function (hi,hv) {
        var title = hv || '--';
        jHeadTr.append( $('<th/>').append(title) );
    });
    var jTbody = $('<tbody/>');
    // 遍历数据集合,每次循环生成一个表格行
    $(datalist).each(function (di,item){
        var jBodyTr =  $('<tr/>').appendTo(jTbody);
        var regionName =item['ZONENAME'];
        // 遍历数据列索引，每次生成一个单元格
        $(index).each(function (ii,iv) {
            var title;
            //alert("iv:"+iv);
            if(iv) {
                title = item[iv];
                //alert("title:"+title);
            }
            title =  title || '--';
            if(iv=='ZONENAME'){
            	jBodyTr.append( $('<td/>').append(title) );
            }
            else{
            	var jBodyTd = $('<td/>').attr("zoneName",regionName).attr("name",iv).appendTo(jBodyTr);
            	jBodyTd.append( $('<a/>').append(title) );
            }
        });
    });
    
    var colNames = [], colIndexes = [],items=[];
    
    for(var i=0;i<header.length;i++){      
    	colNames.push((header[i]) );
    }
    
    for(var i=0;i<index.length;i++){      
    	colIndexes.push((index[i]) );
    }
    $(datalist).each(function (di,item){
    	var xitem = item;
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
    
    $('<table/>').append(jThead).append(jTbody).addClass('gykTbl').appendTo( $('#'+divId) );
}

/**
 * 生成html表格,生成年份链接
 * 
 * @param data
 *            数据列表,如[{name:'张三',age:23},{name:'李四'，age:45}]
 * @param header
 *            列名 ['姓名','年龄']
 * @param index
 *            列索引 ['name','age']
 * @param divId
 *            目标Id
 */
function renderComTableYear(data, header, index, divId) {
    "use strict";
    if( ! divId ){
    	return;
    }
    $('#'+divId).children().remove();
    if( ! header || ! index ){
        return;
    }
    var jThead = $('<thead/>');
    var jHeadTr = $('<tr/>').appendTo(jThead);
    $(header).each(function (hi,hv) {
        var title = hv || '--';
        jHeadTr.append( $('<th/>').append(title) );
    });
    var jTbody = $('<tbody/>');
    // 遍历数据集合,每次循环生成一个表格行
    $(data).each(function (di,item){
        var jBodyTr =  $('<tr/>').appendTo(jTbody);
        var regionCode =item['ARCD'];
        var regionName =item['ARNAME'];
        // 遍历数据列索引，每次生成一个单元格
        $(index).each(function (ii,iv) {
            var title;
            if(iv) {
                title = item[iv];
            }
            title =  title || '--';
            if(iv=='DT'){
            	var jBodyTd = $('<td/>').appendTo(jBodyTr);
            	jBodyTd.append( $('<a/>').append(title).addClass(regionCode).attr("zoneName",regionName).attr("name",iv) );
            }
            else{
            	jBodyTr.append( $('<td/>').append(title) );
            }
        });
    });
    $('<table/>').append(jThead).append(jTbody).addClass('gykTbl').appendTo( $('#'+divId) );
}

/**
 * 生成一个简单的表格-带序号
 * @param containerId 容器id
 *  header 表格头：['头1','头2','头3']
 *  cols 数据列，对应表格头：['col1','col2','col3']
 *  datalist 数据内容
 *  idxname 序号列名,可为null
 * @date 2013-08-15
 * @author lizhiyong
 */
function renderComTable(tabconfig){
	var containerId = tabconfig.cont,
		header = tabconfig.header,
		cols = tabconfig.cols,
		datalist = tabconfig.datalist ,
		idxname = tabconfig.idxname;
	
	//清空容器
	$("#"+containerId).html("");
	
	//验证是否有数据
	if(!datalist || datalist.length === 0 ){
		loadDataError(containerId);
		return;
	}
	//表格
	var $table = $("<table/>");
	//表格头
	var $thead = $("<thead/>");
	var $thead_tr = $("<tr/>");
	if(idxname){
		//序号列
		$($thead_tr).append("<th>"+idxname+"</th>");
	}
	for(var idx in header){
		var $thead_tr_th = $("<th/>");
		$($thead_tr_th).html(header[idx]).appendTo($thead_tr);
	}
	$($thead).append($thead_tr).appendTo($table);
	//表格body
	var $tbody = $("<tbody/>");
	for(var index in datalist){
		var $tbody_tr = $("<tr/>");
		var item = datalist[index];
		if(idxname){
			//序号
			$($tbody_tr).append("<td>"+(parseInt(index)+1)+"</td>");
		}
		for(var i in cols){
			var colname = cols[i];
			var $tbody_tr_td = $("<td/>");
			var coldata = item[colname];
			if(coldata != undefined){
				$($tbody_tr_td).html(coldata);
			}else{
				$($tbody_tr_td).html('--');
			}
			$($tbody_tr).append($tbody_tr_td);
		}
		$($tbody).append($tbody_tr);
	}
	$($table).append($tbody).addClass("gykTbl").appendTo($("#"+containerId));
}

/**
 * @param regionCode(区域代码)
 * @param seriesCode(子项序号)
 * @param regionName(区域名称)
 * @param seriesNm(子项名称)
 * @used in 医疗服务 区域|机构 统计
 */
function compreChart(
		datalist,
		xAxisName,
		seriesIndex,
		seriesNames,
		divId, 
		type ,
		title) {
   var chartData = [],
       chart = {},
       xAxisCateg = [],
       type = type || 'line';
   if( type === 'pie'){
   	   throw new Error('this func can not render a pie chart!');
   }else{
	   $(seriesIndex).each(function (sIdx, sItem) {
	   		var chartSeriesItem = {
	   				name:seriesNames[sIdx],
	   				data:[]
	   		};
	   		$(datalist).each(function(dIdx,dItem){
		   			var ss = new Object();
	   				ss['y'] = dItem[sItem]; 
	   				ss['color'] = colors[dIdx];
	   				chartSeriesItem.data.push(ss);
	   		});
	   		chartData.push(chartSeriesItem);
   		});
	   	$(datalist).each(function(dIdx,dItem){
	   		xAxisCateg.push(dItem[xAxisName]);
	    });
		 chart = new Highcharts.Chart({
		       chart: {
		           type: type,
		           renderTo: divId,
		           plotBackgroundColor: null,
		           plotBorderWidth: null,
		           plotShadow: false
		       },
		       legend: {
		    	    enabled: false
		    	},
		       title: {
		           text : title
		       },
		       credits : {// 不显示highchart标记
					enabled : false
		       },          
		       tooltip: {
		           formatter: function () {
		              // return '<b>' + this.key + '</b>: ' + this.y;
		        	   return '<b>'+ this.series.name +'</b><br/>'+
		               this.x +': '+ this.y ;
		
		           }
		       },
		       xAxis:{
		    	   labels: {
		                 rotation: -8,
		                 align: 'right'
		                 ,style: {
		                     fontSize: '10px',
		                     fontFamily: 'Verdana, sans-serif'
		                 }},
		            categories:xAxisCateg
		       },
		       yAxis:{
		    	   labels: {
						"formatter" : function() {return this.value/1000 + '千';}
					},
			    	min:0,
			       	title:{text:''}
		       },
		       series: chartData
		   });
   }
}

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
 * @param ytitle Y轴标题
 */
function renderMultiEntityChartWithDrill(datalist,entityCode, xAxisName, seriesIndex,seriesNames, divId, chartType , title,ytitle) {
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
	     $(ret).each(function (org_i, org_item) {//遍历机构
	   		var chartSeriesItem = {
	   				name:org_item,
	   				data:[]
	   		};
	   		$(ret_date).each(function (dt_i, dt_Item) {//遍历日期
		   		$(datalist).each(function(dIdx,dItem){//遍历数据集
		   			if(dItem[entityCode]==org_item){
		   			 	     if(dItem[xAxisName]==dt_Item){
		   			 	    	chartSeriesItem.data.push(dItem[seriesIndex] || null);
		   			 	     }
	   			     }
		   		});
		   		if(chartSeriesItem.data.length == dt_i ){
		   			chartSeriesItem.data.push(null);
		   		}
	   		});
	   		
	   		chartData.push(chartSeriesItem);
	   	});
	   	
	   	$(ret_date.sort()).each(function(dt_i,dt_item){
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
	               this.x +ytitle+': '+ this.y ;

	           }
	       },
	       xAxis:{
	       	title: {text:''},
	       	categories:xAxisCateg
	       },
	       yAxis:{
	    	   labels: {
	    		   y: 16,
					"formatter" : function() {return this.value;}
				},
	    	min:0,
	       	title:{text:''}
	       },
	       series: chartData,
	       plotOptions: {
	           column: {
	               pointPadding: 0.2,
	               borderWidth: 0
	           },
	           series : {                                                                                                    
	              		cursor : 'pointer',                                                                                         
	              		point : {                                                                                                   
	              			events : {                                                                                                
	              				click: function(event) {
	              					// 点击项目index
            						var seriesIndex=this.series.name;
            						var xcode;
            						$(datalist).each(function(dIdx,dItem){// 地区代码
			           	    			 if(dItem[entityCode]==seriesIndex){
			           	    				xcode = dItem;
			           	    				return false;
			           	    			 }
			           	    		  });
            						drillOrg(xcode,this.category,this.y);
	              				}
	              			}                                                                                                         
	              		}                                                                                                           
	              	}                   
	       }
	   });
	}

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
 * @param ytitle Y轴标题
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
	     $(ret).each(function (org_i, org_item) {//遍历机构
	   		var chartSeriesItem = {
	   				name:org_item,
	   				data:[]
	   		};
	   		$(ret_date).each(function (dt_i, dt_Item) {//遍历日期
		   		$(datalist).each(function(dIdx,dItem){//遍历数据集
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
	   	
	   	$(ret_date.sort()).each(function(dt_i,dt_item){
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
	               this.x +ytitle+': '+ this.y ;

	           }
	       },
	       xAxis:{
	       	title: {text:''},
	       	categories:xAxisCateg
	       },
	       yAxis:{
	    	   labels: {
	    		   y: 16,
					"formatter" : function() {return this.value;}
				},
	    	min:0,
	       	title:{text:''}
	       },
	       series: chartData
	   });
	}
/*
 * 渲染图表-lizhiyong-20130814
 * 描述：可在一个图表上展现多种类型的统计图：如 线图和柱图同在(一种类型一个,可单个)
 * 参数说明: 如下函数前几行
 * 
 */
function renderChartSpecial(config){
	
	var container = config.container ,//容器id
		datalist = config.datalist ? config.datalist : [],//数据集
		title = config.title ? config.title : '' ,//图表title,可为null
		xAxisCol = config.xAxisCol ? config.xAxisCol : 'XCOL' ,//x轴字段名
		chartTypeCol = config.chartTypeCol ? config.chartTypeCol : 'CHARTTYPE' ,//图表类型字段名，默认值为''
		entityTypeCol = config.entityTypeCol ? config.entityTypeCol : 'ENTITYCODE' ,//数据类型列,用来判断是否为同类数据
		pieConfig = config.pieConfig ? config.pieConfig : {};//饼图某些配置,可为空
		yCol = config.yCol ? config.yCol : 'YCOL';//y轴字段名
	//清空容器
	$("#"+container).html("");
	//验证是否有数据
	if(!datalist || datalist.length === 0 ){
		loadDataError(container);
		return;
	}
	//获取x轴 xAxis.categories
	var xAxiscategories = [],xAxiscategoriesFlag = {};
	//获取yAxis
	var yAxis = [],yAxisFlag = {};
	//数据存储标记
	var dataTemp = {};
	var typeCount = 0;
	for(var idx in datalist){
		var data = datalist[idx];
		//数据类型
		var entityType = data[entityTypeCol];
		if(dataTemp[entityType]){//存在
			if(charttype == 'pie'){//饼图
				dataTemp[entityType].data.push({name:data[xAxisCol],y:data[yCol]});
			}else if(charttype == 'line' || charttype == 'column'){//线图或者柱图
				dataTemp[entityType].data.push(data[yCol]);
			}
		}else{//不存在
			//判断类型
			var charttype = data[chartTypeCol];
			if(charttype == 'pie'){//饼图
				var pieProperties = $.extend({},{/*center:[100,80],size:100,*/showInLegend:true},pieConfig);
				dataTemp[entityType] = {
						type:data[chartTypeCol],
						data:[{name:data[xAxisCol],y:data[yCol]}],
						name:entityType
				};
				$.extend(dataTemp[entityType],pieProperties);
			}else if(charttype == 'line' || charttype == 'column'){//线图或者柱图
				dataTemp[entityType] = {
						type:data[chartTypeCol],
						data:[data[yCol]],
						name:entityType,
						yAxis: typeCount
				};
			}
			typeCount++;
		}
		if(!xAxiscategoriesFlag[data[xAxisCol]]){
			xAxiscategories.push(data[xAxisCol]);
			xAxiscategoriesFlag[data[xAxisCol]] = true;
		}
		if(!yAxisFlag[entityType]){
			if(yAxis.length > 0){
				yAxis.push({title:{text:entityType},opposite:true});
			}else{
				yAxis.push({title:{text:entityType}});
			}
			yAxisFlag[entityType] = true;
		}
	}
	//处理数据
	var seriesData = [];
	for(var i in dataTemp){
		seriesData.push(dataTemp[i]);
	}
	$('#'+container).highcharts({
        chart: {
            zoomType: 'xy'
        },
        title: {
            text: title
        },
        credits : {// 不显示highchart标记
			enabled : false
        },
        xAxis: [{
            categories: xAxiscategories
        }],
        yAxis:yAxis,
        tooltip: {
            shared: true
        },
        series: seriesData
    });
	
}

/**
* @param data  数据列表
* @param xAxisName x轴指标项名: 'name'
* @param seriesNames 统计指标项名: ['年龄','成绩']
* @param seriesIndex 统计指标项索引: ['age','score']
* @param divId 渲染的目标Id
* @param type 图表类型，参见highcharts类型
* @param title 图表标题
*/
function renderChart(data, xAxisName, seriesIndex,seriesNames, divId, type , title) {
   "use strict";
   var chartData = [],
       chart = {},
       xAxisCateg = [],
       type = type || 'column';
   if( type === 'pie'){

   	throw new Error('this func can not render a pie chart!');
   }else{
   	//指标列表
   	$(seriesIndex).each(function (sIdx, sItem) {
   		var chartSeriesItem = {
   				name:seriesNames[sIdx],
   				data:[]
   		};
   		$(data).each(function(dIdx,dItem){//获得 ‘age’列的所有值
   			if(dItem[sItem]!=0){
   				chartSeriesItem.data.push(dItem[sItem]);
   			}else{
   				chartSeriesItem.data.push(0);
   			}
   		});
   		chartData.push(chartSeriesItem);
   	});
   	
   	$(data).each(function(dIdx,dItem){
   		xAxisCateg.push(dItem[xAxisName] || null);
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
               return '<b>' + this.key + '</b>: ' + this.y;
           }
       },
       xAxis:{
       	title: {text:''},
       	/*labels: {
                rotation: -45,
                align: 'right'
                ,style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            },*/
       	categories:xAxisCateg
       },
       yAxis:{
       	min:0,
       	title:{text:''}
       },
   	plotOptions : {         
   		column : {            
   			pointPadding : 0.2, 
   			borderWidth : 0     
   		}                     
   	},                      
       series: chartData
   });
}

/**
 * @param divId
 * 数据加载错误时候显示暂无数据
 */
function loadDataError(divId) {
	$('#'+divId).children().remove();
	$('#'+divId).append("<center><font style='color:red;font-size:12px'>暂无数据！</font></center>");
}