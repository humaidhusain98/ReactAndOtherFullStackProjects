import {HorizontalBar} from 'react-chartjs-2';

const BarChart = ({code}) => {
    const state = {
        labels: ['Java', 'C++', 'Python',
                 'JavaScript'],
        datasets: [
          {
            label: 'Time in milliseconds',
            
            backgroundColor: [
              'rgba(22,200,100,150)',
              'rgba(0,1,200,1)',
              'rgba(100,22,200,1)',
              'rgba(150,22,200,1)'

            ],
            borderColor: 'rgba(0,0,0,1)',
            borderWidth: 2,
            hoverBackgroundColor: 'rgba(255,99,132,0.4)',
            hoverBorderColor: 'rgba(255,99,132,1)',
            data: [code.Java,code.Cplusplus, code.Python, code.Javascript]
          }
        ]
      }


    return (  
        <HorizontalBar
          data={state}
          options={{
            title:{
              display:true,
              text:'Time to run. Lower is Better',
              fontSize:20
            },
            legend:{
              display:false,
              position:'right',
        
            }
          }}

          
        />
    );
}
 
export default BarChart;