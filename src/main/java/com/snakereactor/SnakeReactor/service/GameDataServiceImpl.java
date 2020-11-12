package com.snakereactor.SnakeReactor.service;

import com.snakereactor.SnakeReactor.model.Gamelog;
import com.snakereactor.SnakeReactor.repository.GamelogRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameDataServiceImpl implements GameDataService {

    @Autowired
    private GamelogRepository gamelogRepository;

    @Autowired
    private AwsS3Service S3Service;

    public String createDemographicsPlot(Model model){
        //FETCH GAME LOGS FROM DB
        List<Gamelog> gamelogs = gamelogRepository.findAll();

        //Create HashMap for Data;
        Map<String, Integer> zmap = new HashMap<>(); //Gen Z
        Map<String, Integer> mmap = new HashMap<>(); //Millenials
        Map<String, Integer> ymap = new HashMap<>(); //Gen Y
        Map<String, Integer> bmap = new HashMap<>(); //Boomers
        DefaultCategoryDataset barData = new DefaultCategoryDataset();

        for(Gamelog gamelog: gamelogs){
            if(gamelog.getAge() == 1){
                if(!zmap.containsKey(gamelog.getGender())){
                    zmap.put(gamelog.getGender(), 1);
                } else {
                    zmap.put(gamelog.getGender(), zmap.get(gamelog.getGender())+1);
                }
            } else if (gamelog.getAge() == 2){
                if(!mmap.containsKey(gamelog.getGender())){
                    mmap.put(gamelog.getGender(), 1);
                } else {
                    mmap.put(gamelog.getGender(), mmap.get(gamelog.getGender())+1);
                }
            } else if (gamelog.getAge() == 3){
                if(!ymap.containsKey(gamelog.getGender())){
                    ymap.put(gamelog.getGender(), 1);
                } else {
                    ymap.put(gamelog.getGender(), ymap.get(gamelog.getGender())+1);
                }
            } else if (gamelog.getAge() == 4){
                if(!bmap.containsKey(gamelog.getGender())){
                    bmap.put(gamelog.getGender(), 1);
                } else {
                    bmap.put(gamelog.getGender(), bmap.get(gamelog.getGender())+1);
                }
            }
        }
        zmap.forEach((k,v) -> barData.addValue(v, k, "Gen Z"));
        mmap.forEach((k,v) -> barData.addValue(v, k, "Millenial"));
        ymap.forEach((k,v) -> barData.addValue(v, k, "Gen Y"));
        bmap.forEach((k,v) -> barData.addValue(v, k, "Boomer"));

        JFreeChart barChart = ChartFactory.createBarChart(
                "Demographics",
                "Gender",
                "Number of Players",
                barData
        );

        try {
            ChartUtils.saveChartAsPNG(
                    new File("/Users/harris/Documents/HackReactor/MVP - Snake/Charts/demographics.png"),
                    barChart,
                    1200,
                    900
            );
        } catch (Exception err){
            System.err.println(err);
        } finally {
            System.out.println("Saved Chart!");
            S3Service.uploadFile("/Users/harris/Documents/HackReactor/MVP - Snake/Charts/demographics.png", "demographics.png");
        }

        model.addAttribute("plotName", "Snake Reactor Data - Demographics Chart");
        model.addAttribute("chartURL", "https://snake-reactor-data.s3-us-west-1.amazonaws.com/demographics.png");

        return "plot";
    }

    public String createDemisePlot(Model model){
        //FETCH GAME LOGS FROM DB
        List<Gamelog> gamelogs = gamelogRepository.findAll();

        //Create Dataset
        DefaultPieDataset dataset = new DefaultPieDataset();

        //CREATE DATALIST
        Map<String, Integer> mapCount = new HashMap<>();

        for(Gamelog gamelog: gamelogs){
            if(mapCount.get(gamelog.getDemise()) == null){
                mapCount.put(gamelog.getDemise(), 1);
            } else {
                mapCount.put(gamelog.getDemise(), mapCount.get(gamelog.getDemise()) + 1);
            }
        }

        mapCount.forEach((key, value) -> {
           dataset.setValue(key, value);
        });

        //CREATE CHART
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Reason for Demise",
                dataset,
                true,
                true,
                false
        );

        try {
            ChartUtils.saveChartAsPNG(
                    new File("/Users/harris/Documents/HackReactor/MVP - Snake/Charts/reasonfordemise.png"),
                    pieChart,
                    1200,
                    900
            );
        } catch (Exception err){
            System.err.println(err);
        } finally {
            System.out.println("Saved Chart!");
            S3Service.uploadFile("/Users/harris/Documents/HackReactor/MVP - Snake/Charts/reasonfordemise.png", "reasonfordemise.png");
        }

        model.addAttribute("plotName", "Snake Reactor Data - Reason for Demise Chart");
        model.addAttribute("chartURL", "https://snake-reactor-data.s3-us-west-1.amazonaws.com/reasonfordemise.png");

        return "plot";
    }

    public String createAverageScorePlot(Model model){
        //FETCH GAME LOGS FROM DB
        List<Gamelog> gamelogs = gamelogRepository.findAll();

        //Create Dataset
        HistogramDataset dataset = new HistogramDataset();

        int zsize = 0;
        int msize = 0;
        int xsize = 0;
        int bsize = 0;

        for(Gamelog gamelog: gamelogs){
            double value = gamelog.getScore();
            if(gamelog.getAge() == 1){
                zsize += 1;
            } else if(gamelog.getAge() == 2){
                msize += 1;
            } else if (gamelog.getAge() == 3){
                xsize += 1;
            } else if (gamelog.getAge() == 4){
                bsize += 1;
            }
        }

        double[] zscore = new double[zsize];
        double[] mscore = new double[msize];
        double[] xscore = new double[xsize];
        double[] bscore = new double[bsize];

        int zindex = 0;
        int mindex = 0;
        int xindex = 0;
        int bindex = 0;

        for(Gamelog gamelog: gamelogs){
            if(gamelog.getAge() == 1){
                zscore[zindex] = gamelog.getScore();
                zindex += 1;
            } else if(gamelog.getAge() == 2){
                mscore[mindex] = gamelog.getScore();
                mindex += 1;
            } else if (gamelog.getAge() == 3){
                xscore[xindex] = gamelog.getScore();
                xindex += 1;
            } else if (gamelog.getAge() == 4){
                bscore[bindex] = gamelog.getScore();
                bindex += 1;
            }
        }

        dataset.addSeries("Gen Z", zscore, zsize);
        dataset.addSeries("Millennial", mscore, msize);
        dataset.addSeries("Gen X", xscore, xsize);
        dataset.addSeries("Boomer", bscore, bsize);

        //Create Chart
        JFreeChart histogram = ChartFactory.createHistogram(
                "Age vs Scores",
                "Normal Distribution of Number",
                "Score",
                dataset
        );

        try {
            ChartUtils.saveChartAsPNG(
                    new File("/Users/harris/Documents/HackReactor/MVP - Snake/Charts/demographicscores.png"),
                    histogram,
                    1200,
                    900
            );
        } catch (Exception err){
            System.err.println(err);
        } finally {
            System.out.println("Saved Chart!");
            S3Service.uploadFile("/Users/harris/Documents/HackReactor/MVP - Snake/Charts/demographicscores.png", "demographicscores.png");
        }

        model.addAttribute("plotName", "Snake Reactor Data - Demographics vs Scores Chart");
        model.addAttribute("chartURL", "https://snake-reactor-data.s3-us-west-1.amazonaws.com/demographicscores.png");

        return "plot";
    }

    public String createPurchaseConsiderationPlot(Model model){
        //FETCH GAME LOGS FROM DB
        List<Gamelog> gamelogs = gamelogRepository.findAll();

        //Create Dataset
        HistogramDataset dataset = new HistogramDataset();
        HistogramDataset purchasedDataset = new HistogramDataset();

        double[] zscore = new double[500];

        int z_size = 0;
        int p_size = 0;
        int n_size = 0;

        int zindex = 0;
        int pindex = 0;
        int nindex = 0;

        for(Gamelog gamelog: gamelogs.subList(0,500)){
            zscore[zindex] = gamelog.getScore();
            zindex += 1;
            if(gamelog.getPurchased_reward_bool()){
                p_size += 1;
            } else {
                n_size += 1;
            }
        }


        double[] pscore = new double[p_size];
        double[] nscore = new double[n_size];

        for(Gamelog gamelog: gamelogs.subList(0,500)){
            if(gamelog.getPurchased_reward_bool()){
                pscore[pindex] = gamelog.getTime_in_purchase_modal();
                pindex += 1;
            } else {
                nscore[nindex] = gamelog.getTime_in_purchase_modal();
                nindex += 1;
            }
        }

        dataset.addSeries("Game Iteration", zscore, 500);
        purchasedDataset.addSeries("Purchased", pscore, 500);
        purchasedDataset.addSeries("Did not purchase", nscore, 500);

        //Create Chart
        JFreeChart histogram = ChartFactory.createHistogram(
                "Purchase Time Consideration",
                "Occurrences",
                "Seconds",
                dataset
        );

        JFreeChart purchased_histogram = ChartFactory.createHistogram(
                "Purchased Boolean",
                "Incident",
                "Purchased",
                purchasedDataset
        );

        try {
            ChartUtils.saveChartAsPNG(
                    new File("/Users/harris/Documents/HackReactor/MVP - Snake/Charts/purchaseconsideration.png"),
                    histogram,
                    1200,
                    900
            );
            ChartUtils.saveChartAsPNG(
                    new File("/Users/harris/Documents/HackReactor/MVP - Snake/Charts/purchaseconsideration2.png"),
                    purchased_histogram,
                    1200,
                    900
            );
        } catch (Exception err){
            System.err.println(err);
        } finally {
            System.out.println("Saved Chart!");
            S3Service.uploadFile("/Users/harris/Documents/HackReactor/MVP - Snake/Charts/purchaseconsideration2.png", "purchaseconsideration2.png");
        }

        model.addAttribute("plotName", "Snake Reactor Data - Purchase Consideration Time");
        model.addAttribute("chartURL", "https://snake-reactor-data.s3-us-west-1.amazonaws.com/purchaseconsideration.png");

        return "plot";
    }

}
