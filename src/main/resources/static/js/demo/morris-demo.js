$(function() {
    Morris.Line({
        element: 'morris-one-line-chart',
            data: [
                { year: '2014', value: 1000 },
                { year: '2015', value: 2000 },
                { year: '2016', value: 1000 },
                { year: '2017', value: 3200 },
                { year: '2018', value: 800 },
                { year: '2019', value: 2000 },
                { year: '2020', value: 4000 },
            ],
        xkey: 'year',
        ykeys: ['value'],
        resize: true,
        hideHover: 'auto',
        lineWidth:4,
        labels: ['Value'],
        lineColors: ['#1ab394'],
    });
});
