package com.spring.webservice.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.webservice.exception.ServiceException;
import com.spring.webservice.model.User;

/**
 * Rest Service mapping based on url mapping & requestmethod
 * 
 * @author Hemantha
 */
@RestController
public class ImageRestController {
	
	 @RequestMapping(value="/image/{keyId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	    public ResponseEntity<BufferedImage> getKeyScreenshot(@PathVariable String keyId) {
		 
		 InputStream inputStream = new InputStream();
		    BufferedImage body;
		    MediaType contentType = MediaType.IMAGE_PNG;
		    Iterator<ImageReader> imageReaders = ImageIO.getImageReadersByMIMEType(contentType.toString());
		    if (imageReaders.hasNext()) {
		        ImageReader imageReader = imageReaders.next();
		        ImageReadParam irp = imageReader.getDefaultReadParam();
		        imageReader.setInput(new MemoryCacheImageInputStream(inputStream), true);
		        body = imageReader.read(0, irp);
		    } else {
		        throw new HttpMessageNotReadableException("Could not find javax.imageio.ImageReader for Content-Type ["
		                + contentType + "]");
		    }
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.IMAGE_PNG);
		    return new ResponseEntity<BufferedImage>(body, headers, HttpStatus.OK);
	       
	    }
	 
		/*@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
		public ResponseEntity<User> getUser(@PathVariable("id") String id, UriComponentsBuilder ucBuilder)
				throws ServiceException {

			User user = userService.findUserById(id);
			if (user == null) {
				// return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
				throw new ServiceException("User with id: " + id + " does not Exist");
			}

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(id).toUri());
			return new ResponseEntity<User>(user,headers, HttpStatus.OK);
		}*/


}
