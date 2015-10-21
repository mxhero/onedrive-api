package com.mxhero.plugin.cloudstorage.onedrive.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * The Class Parameters.
 */
public class Parameters {

	/** The parameters map. */
	private Map<String, String> parametersMap = new HashMap<>();
	
	/**
	 * Query.
	 *
	 * @param query the query
	 * @return the o data
	 */
	public Parameters query(String query){
		this.parametersMap.put("q",query);
		return this;
	}
	
	/**
	 * Expand.
	 *
	 * @param expand the expand
	 * @return the o data
	 */
	public Parameters expand(String expand){
		this.parametersMap.put("expand",expand);
		return this;
	}
	
	/**
	 * Select.
	 *
	 * @param select the select
	 * @return the o data
	 */
	public Parameters select(String select){
		this.parametersMap.put("select",select);
		return this;
	}
	
	/**
	 * Skip token.
	 *
	 * @param skipToken the skip token
	 * @return the o data
	 */
	public Parameters skipToken(String skipToken){
		this.parametersMap.put("skipToken",skipToken);
		return this;
	}
	
	/**
	 * Top.
	 *
	 * @param top the top
	 * @return the o data
	 */
	public Parameters top(String top){
		this.parametersMap.put("top",top);
		return this;
	}
	
	/**
	 * Orderby.
	 *
	 * @param orderby the orderby
	 * @return the o data
	 */
	public Parameters orderby(String orderby){
		this.parametersMap.put("orderby",orderby);
		return this;
	}
	
	/**
	 * Filter.
	 *
	 * @param filter the filter
	 * @return the o data
	 */
	public Parameters filter(String filter){
		this.parametersMap.put("filter",filter);
		return this;
	}
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<NameValuePair> list(){
		List<NameValuePair> pairs = new ArrayList<>();
		for(Entry<String, String> parameter : this.parametersMap.entrySet()){
			if(StringUtils.isNotBlank(parameter.getValue())){
				pairs.add(new BasicNameValuePair(parameter.getKey(), parameter.getValue()));
			}
		}
		return pairs;
	}
	
}
