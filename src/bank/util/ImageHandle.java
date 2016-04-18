package bank.util;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ImageHandle {

	// 图片名字要传入，所需要的横纵值需要传入。标题。
	public ImageHandle() {
		// TODO Auto-generated constructor stub
	}

	public Dataset getDataSet() {

		return null;
	}

	public void saveImage(ImageEntry entry) throws IOException {
		String nameString = entry.getImageName();
		if (nameString.contains("等级")) {
			saveImageStringAxis(entry);
		} else {
			if (nameString.contains("排名")) {
				saveImageRangeAxis(entry);
			} else {
				saveImageResultAxis(entry);
			}
		}

	}

	public void saveImageRangeAxis(ImageEntry entry) throws IOException {

		String nameString = entry.getImageName();
	//	JOptionPane.showMessageDialog(null, "start to generate image for "+nameString);
		//创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		//设置标题字体
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
		//设置轴向字体
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		//设置图例字体
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		//应用主题样式
		ChartFactory.setChartTheme(mChartTheme);

	//	JOptionPane.showMessageDialog(null, "the theme inintialed for "+nameString);
		XYSeries series = new XYSeries("");

		for (int i = 0; i < entry.getOrdinate().length; i++) {
			series.add(entry.getAbscissa()[i], entry.getOrdinate()[i]);
		}

		XYSeriesCollection xySeriesCollection = new XYSeriesCollection(series);

		JFreeChart jfreechart = ChartFactory.createXYLineChart(nameString,
				"年份", "排名", xySeriesCollection, PlotOrientation.VERTICAL, true,
				true, false);
		XYPlot plot = (XYPlot) jfreechart.getPlot();

		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot
				.getRenderer();
		renderer.setBaseItemLabelsVisible(true);
		NumberFormat format = NumberFormat.getNumberInstance();
		NumberFormat year = NumberFormat.getNumberInstance();
		year.setMaximumFractionDigits(0);
		format.setMaximumFractionDigits(2); // etc.
		XYItemLabelGenerator generator = new StandardXYItemLabelGenerator(
				"{0} {1} {2}", year, format);
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(true);
		// renderer.setSeriesItemLabelsVisible(true);
/*
		NumberAxis numberAxis=(NumberAxis) plot.getRangeAxis();
		numberAxis.setTickLabelFont(new Font("sans-serif",Font.PLAIN,11));
		ValueAxis categoryAxis=plot.getDomainAxis();
		categoryAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));


		jfreechart.getXYPlot().getRangeAxis().setInverted(true);
*/
		File fos_jpg = new File("test/" + nameString + ".jpg");

		// 输出到哪个输出流
		ChartUtilities.saveChartAsJPEG(fos_jpg, jfreechart, // 统计图表对象
				700, // 宽
				400);

	}

	public void saveImageResultAxis(ImageEntry entry) throws IOException {

		String nameString = entry.getImageName();
		
		//创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		//设置标题字体
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
		//设置轴向字体
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		//设置图例字体
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		//应用主题样式
		ChartFactory.setChartTheme(mChartTheme);

		XYSeries series = new XYSeries("");

		for (int i = 0; i < entry.getOrdinate().length; i++) {
			series.add(entry.getAbscissa()[i], entry.getOrdinate()[i]);
		}

		XYSeriesCollection xySeriesCollection = new XYSeriesCollection(series);

		JFreeChart jfreechart = ChartFactory.createXYLineChart(nameString,
				"年份", "结果", xySeriesCollection, PlotOrientation.VERTICAL, true,
				true, false);

		jfreechart.getXYPlot().getRangeAxis().setInverted(true);

		XYPlot plot = jfreechart.getXYPlot();

		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot
				.getRenderer();
		renderer.setBaseItemLabelsVisible(true);
		NumberFormat format = NumberFormat.getNumberInstance();
		NumberFormat year = NumberFormat.getNumberInstance();
		year.setMaximumFractionDigits(0);
		format.setMaximumFractionDigits(2); // etc.
		XYItemLabelGenerator generator = new StandardXYItemLabelGenerator(
				"{0} {1} {2}", year, format);
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(true);

		File fos_jpg = new File("test/" + nameString + ".jpg");

		/*NumberAxis numberAxis=(NumberAxis) plot.getRangeAxis();
		numberAxis.setTickLabelFont(new Font("sans-serif",Font.PLAIN,11));
		ValueAxis categoryAxis=plot.getDomainAxis();
		categoryAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
*/

		// 输出到哪个输出流
		ChartUtilities.saveChartAsJPEG(fos_jpg, jfreechart, // 统计图表对象
				700, // 宽
				400);

	}

	public void saveImageStringAxis(ImageEntry entry) throws IOException {
		String nameString = entry.getImageName();

		// String[] axisStrings = { "差", "中", "良", "较优", "优" };
		
		//创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		//设置标题字体
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
		//设置轴向字体
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		//设置图例字体
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		//应用主题样式
		ChartFactory.setChartTheme(mChartTheme);
		
		
		

		String[] xStrings = new String[entry.getAbscissa().length];
		double[] xYear = new double[entry.getAbscissa().length];

		double[] y = new double[entry.getOrdinate().length];
		for (int i = 0; i < entry.getAbscissa().length; i++) {
			y[i] = entry.getOrdinate()[i];
			xStrings[i] = String.valueOf(entry.getAbscissa()[i]);
			xYear[i] = i;
		}
		DefaultXYDataset dataset = new DefaultXYDataset();
		// System.out.println("length xyear "+xYear.length +" y "+y.length);
		double[][] stringAxisData = { xYear, y };

		dataset.addSeries("结果", stringAxisData);

		// ValueAxis xAxis = new NumberAxis("年份");

		ValueAxis xAxis = new SymbolAxis("年份", xStrings);
		ValueAxis yAxis = new SymbolAxis("等级", new String[] { "差", "中", "良",
				"较优", "优" });

		XYItemRenderer renderer = new XYLineAndShapeRenderer();

		XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
		/*
		NumberAxis numberAxis=(NumberAxis) plot.getRangeAxis();
		numberAxis.setTickLabelFont(new Font("sans-serif",Font.PLAIN,11));
		ValueAxis categoryAxis=plot.getDomainAxis();
		categoryAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
*/
		JFreeChart jfreechart = new JFreeChart(nameString, new Font("Tahoma",
				0, 18), plot, true);

		// 创建文件输出流

		File fos_jpg = new File("test/" + nameString + ".jpg");
		// 输出到哪个输出流
		ChartUtilities.saveChartAsJPEG(fos_jpg, jfreechart, // 统计图表对象
				700, // 宽
				400);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// abandon
	public void saveImageNumberAxis1(ImageEntry entry) throws IOException {
		String nameString = entry.getImageName();
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();

		for (int i = 0; i < entry.getOrdinate().length; i++) {
			defaultcategorydataset.addValue(entry.getOrdinate()[i], " ",
					String.valueOf(entry.getAbscissa()[i]));
		}
		JFreeChart jfreechart = ChartFactory.createLineChart(nameString, "年份",
				"稳定性", defaultcategorydataset, PlotOrientation.VERTICAL, true,
				true, false);

		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
		categoryplot.setBackgroundPaint(null);
		categoryplot.setRangeGridlinePaint(Color.white);

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) categoryplot
				.getRenderer();

		jfreechart.getXYPlot().getRangeAxis().setInverted(true);

		renderer.setShapesVisible(true);
		DecimalFormat decimalformat1 = new DecimalFormat("######");
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator(
				"{2}", decimalformat1));
		renderer.setItemLabelsVisible(true);
		renderer.setSeriesVisible(true);

		File fos_jpg = new File("test/" + nameString + ".jpg");
		// 输出到哪个输出流
		ChartUtilities.saveChartAsJPEG(fos_jpg, jfreechart, // 统计图表对象
				700, // 宽
				400);
	}

}
