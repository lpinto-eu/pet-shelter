App.BarGraphComponent = Ember.Component.extend({
	tagName: 'svg',

	didInsertElement: function(){
		var chartData = this.get('data');

        //var margin = {top: 5, right: 6, bottom: 6, left: 7};
        var margin = {top: 20, right: 6, bottom: 20, left: 20};
		var width = 175 - margin.left - margin.right;
		var height = 150 - margin.top - margin.bottom;

        var x = d3.scale.ordinal()
        .domain(d3.range(1, 13))
        .rangeRoundBands([0, width], .1);

        var maxY = d3.max(chartData) % 4 + d3.max(chartData);
        var y = d3.scale.linear()
        .domain([0, maxY])
        .range([height, 0]);

        var xAxis = d3.svg.axis()
        .scale(x)
        .orient("bottom");

        var yAxis = d3.svg.axis()
        .scale(y)
        .orient("left")
        .ticks(4);

        var id = this.$().attr('id');
        var chartAndAxis = d3.select("#"+id)
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
        var chart = chartAndAxis
        .append("g")

        chart.append("g")
        .attr("class", "x axis")
        .attr("transform", "translate(0, " + height + ")")
        .call(xAxis);

        chart.append("g")
        .attr("class", "y axis")
        .call(yAxis);

        var bar = chart.selectAll(".bar")
        .data(chartData)
        .enter().append("rect")
//        .attr("transform", function(d, i) {
//            return "translate(" + x(i + 1) + ", 0)" // i is 0..11 need 1..12
//        })
        .attr("class", "bar")
        .attr("x", function(d, i) { return x(i + 1); })
        .attr("y", function(d) { return y(d); })
        .attr("height", function(d) { return height - y(d);})
        .attr("width", x.rangeBand());

        chartAndAxis.append("text")
        .attr("x", (width / 2))             
        .attr("y", 0 - 0.2 * margin.top)             
        .attr("text-anchor", "middle")  
        .style("font-size", "12px") 
        .text("Admissions by month");
	}
});
