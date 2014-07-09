package DTO;

import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class APIs
{
	@Element
    public List<API> API ;
}
