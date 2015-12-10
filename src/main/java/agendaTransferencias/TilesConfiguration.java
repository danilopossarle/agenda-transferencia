package agendaTransferencias;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Classe de configuração do tiles
 * 
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
@Configuration
public class TilesConfiguration {

	/**
	 * Inicializa o tiles e define o arquivo de configuração do mesmo.
	 * 
	 * @return {@link TilesConfigurer}
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] { "WEB-INF/tiles/tiles.xml" });
		configurer.setCheckRefresh(true);
		return configurer;
	}

	/**
	 * Inicializa {@link TilesViewResolver} responsável por encontrar os templates do tiles.
	 * 
	 * @return {@link TilesViewResolver}
	 */
	@Bean
	public UrlBasedViewResolver tilesViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
}
