package eu.lpinto.petshelter.api.dts;

import eu.lpinto.petshelter.entities.Organization;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author mostardinha - pedro.mostardinha@gmail.com
 */
public enum OrganizationsDTS implements DTS<Organization, String> {

    SINGLETON;

    @Override
    public String transform(Organization input) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        sb.append(input.getId()).append(";");
        sb.append(input.getName()).append(";");
        sb.append(sdf.format(input.getCreated().getTime())).append(";");
        sb.append(sdf.format(input.getUpdated().getTime()));

        return sb.toString();
    }

    public String transform(List<Organization> in) {
        if (in == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (Organization org : in) {
            sb.append(transform(org));
            counter++;

            if (counter != in.size()) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
