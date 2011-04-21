package daemon.plugin.maven;import org.apache.commons.io.FileUtils;import org.apache.maven.artifact.Artifact;import org.apache.maven.plugin.AbstractMojo;import org.apache.maven.plugin.MojoExecutionException;import org.apache.maven.plugin.MojoFailureException;import org.apache.maven.project.MavenProject;import java.io.File;import java.io.IOException;import java.util.Map;public class JsvcMojo extends AbstractMojo {    private static final String COMMONS_DAEMON_ARTIFACT_ID = "commons-daemon";    private static final String COMMONS_DAEMON_GROUP_ID = "commons-daemon";    /**     * @parameter expression="${project}"     * @required     * @readonly     */    private MavenProject mavenProject;    /**     * @parameter expression="${project.build.directory}"     * @required     * @readonly     */    private File projectBuildDirectory;    @Override    public void execute() throws MojoExecutionException, MojoFailureException {        final Map<String, Artifact> artifactMap = mavenProject.getArtifactMap();        final Artifact artifact = artifactMap.get(COMMONS_DAEMON_GROUP_ID + ":" + COMMONS_DAEMON_ARTIFACT_ID);        if (null == artifact)            throw new MojoExecutionException("Could not retrieve commons-daemon artifact");        try {            FileUtils.copyFileToDirectory(artifact.getFile(), projectBuildDirectory);        } catch (IOException e) {            throw new MojoExecutionException(                    String.format("Failed to copy artifact [%s] to [%s]", artifact, projectBuildDirectory), e);        }    }}